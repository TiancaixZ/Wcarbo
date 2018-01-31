package com.example.chenguozhen.wcarbo.Fragment_weibo_detailed;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.chenguozhen.wcarbo.Adapter.RecyclerViewAdapter.repost_list_adapter;
import com.example.chenguozhen.wcarbo.Bean.JSON.Status;
import com.example.chenguozhen.wcarbo.Bean.Repost;
import com.example.chenguozhen.wcarbo.Interface.HttpListener;
import com.example.chenguozhen.wcarbo.Interface.RecyclerViewItemClickLisntner;
import com.example.chenguozhen.wcarbo.module.base.BaseListFragment;
import com.example.chenguozhen.wcarbo.AsyncTask.BaseAsyncTask;
import com.example.chenguozhen.wcarbo.utils.HttpUtil;
import com.example.chenguozhen.wcarbo.utils.JSONUitily;
import com.example.chenguozhen.wcarbo.utils.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenguozhen on 2018/1/18.
 */

public class Fragment_weibo_detailed_reposts extends BaseListFragment implements HttpListener,RecyclerViewItemClickLisntner{

    private String detail_idstr;
    private long max_id = 0;
    private String url = "https://api.weibo.com/2/statuses/repost_timeline.json?source=3867086258";
    private List<Status> repostList = new ArrayList<Status>();
    private repost_list_adapter adapter;
    private ProgressDialog dialog;
    private Request request = null;

    public static Fragment_weibo_detailed_reposts newInstance(String detial_idstr){
        Bundle args = new Bundle();
        args.putString("detail",detial_idstr);

        Fragment_weibo_detailed_reposts fragment_weibo_detailed_reposts = new Fragment_weibo_detailed_reposts();
        fragment_weibo_detailed_reposts.setArguments(args);
        return fragment_weibo_detailed_reposts;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        repostList.clear();
    }

    @Override
    public RecyclerView.Adapter adapter() {
        adapter = new repost_list_adapter(repostList,Fragment_weibo_detailed_reposts.this,this);
        return adapter;
    }

    @Override
    protected void Create_Content(String token) {
        detail_idstr = getArguments().getString("detail");
        dialog = ProgressDialog.show(getContext(), "", "加载中，请稍后……");
        request = Utility.budiler(url,max_id,token,detail_idstr);
        send(request);
    }

    @Override
    protected void SwipeRefresh_Refresh(String token) {
        if (max_id != 0) {
            request = Utility.budiler(url,max_id,token,detail_idstr);
            send(request);
        }
    }

    @Override
    protected void ScrollListener_LoadMore(String token) {
       if (max_id != 0){
           request = Utility.budiler(url,max_id,token,detail_idstr);
           send(request);
       }
    }

    @Override
    public void ItemClick(int position) {

    }

    @Override
    public void success(String responseData) {
        final Repost repost = JSONUitily.repost(responseData);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (repost.getReposts() != null){
                    repostList.addAll(repost.getReposts());
                    max_id = repost.getNext_cursor();
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
}
