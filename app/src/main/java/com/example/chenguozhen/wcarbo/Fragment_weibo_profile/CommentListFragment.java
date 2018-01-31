package com.example.chenguozhen.wcarbo.Fragment_weibo_profile;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.chenguozhen.wcarbo.Adapter.RecyclerViewAdapter.comment_list_adapter;
import com.example.chenguozhen.wcarbo.Bean.Comments;
import com.example.chenguozhen.wcarbo.Bean.JSON.Comment;
import com.example.chenguozhen.wcarbo.Interface.HttpListener;
import com.example.chenguozhen.wcarbo.Interface.RecyclerViewItemClickLisntner;
import com.example.chenguozhen.wcarbo.module.base.BaseListFragment;
import com.example.chenguozhen.wcarbo.utils.HttpUtil;
import com.example.chenguozhen.wcarbo.utils.JSONUitily;
import com.example.chenguozhen.wcarbo.utils.Utility;
import com.example.chenguozhen.wcarbo.wcarbo;
import com.example.chenguozhen.wcarbo.widget.CommentChoiceFragment;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.chenguozhen.wcarbo.widget.CommentChoiceFragment.EXTRA_DATE;

/**
 * Created by chenguozhen on 2018/1/30.
 */

public abstract class CommentListFragment extends BaseListFragment implements RecyclerViewItemClickLisntner,HttpListener{
    private long max_id = 0;
    private List<Comment> commentsList = new ArrayList<Comment>();
    private comment_list_adapter adapter;
    private Request request = null;
    private ProgressDialog dialog;
    private Comments mComment;
    private int Position = 0;

    public static final int REQUEST_DATE = 0;

    public static final int comment = 12;
    public static final int byme = 10;
    public static final int me = 11;

    @Override
    public void onDetach() {
        super.onDetach();
        commentsList.clear();
    }

    @Override
    public RecyclerView.Adapter adapter() {
        adapter = new comment_list_adapter(commentsList, CommentListFragment.this,this);
        return adapter;
    }

    @Override
    protected void Create_Content(String token) {
        dialog = ProgressDialog.show(getContext(), "", "加载中，请稍后……");
        request = Utility.byme_builder(url(),max_id,token);
        send(request);
    }

    @Override
    protected void SwipeRefresh_Refresh(String token) {
        max_id = 0;
        request = Utility.byme_builder(url(),max_id,token);
        send(request);
        commentsList.clear();
    }

    @Override
    protected void ScrollListener_LoadMore(String token) {
        if (max_id != 0) {
            request = Utility.byme_builder(url(),max_id,token);
            send(request);
        }
    }

    @Override
    public void ItemClick(int position) {
        Position = position;
        String cid = commentsList.get(position).getIdstr();
        String id = mComment.getStatus().getIdstr();
        CommentChoiceFragment choiceFragment = CommentChoiceFragment.newInstance(id,cid,type());
        choiceFragment.setTargetFragment(CommentListFragment.this,REQUEST_DATE);
        choiceFragment.show(getFragmentManager());
    }

    @Override
    public void success(String responseData) {
        final Comments comment = JSONUitily.bytomentions(responseData);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mComment = comment;
                if (comment.getComments() != null){
                    commentsList.addAll(comment.getComments());
                    max_id = comment.getNext_cursor();
                    adapter.notifyDataSetChanged();
                }
                dialog.dismiss();
            }
        });
    }

    @Override
    public void failed() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(),"网络出现问题",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void send(Request request){
        HttpUtil httpUtil = new HttpUtil(this);
        httpUtil.Data_update(request);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == REQUEST_DATE){
            final boolean deleted = data.getExtras().getBoolean(EXTRA_DATE);
            Log.d("deleted",deleted+"value");
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (deleted){
                        adapter.deleteItem(Position);
                        Toast.makeText(wcarbo.getContext(),"已经删除",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(wcarbo.getContext(),"无法删除",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    protected abstract String url();

    protected abstract int type();

}
