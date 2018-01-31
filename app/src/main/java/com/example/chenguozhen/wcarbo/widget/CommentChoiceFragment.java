package com.example.chenguozhen.wcarbo.widget;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.chenguozhen.wcarbo.Fragment_add.Fragment_Addweibo;
import com.example.chenguozhen.wcarbo.Fragment_weibo_profile.CommentListFragment;
import com.example.chenguozhen.wcarbo.Interface.HttpListener;
import com.example.chenguozhen.wcarbo.activity.ClickButtonActivity;
import com.example.chenguozhen.wcarbo.activity.MainActivity;
import com.example.chenguozhen.wcarbo.utils.HttpUtil;
import com.example.chenguozhen.wcarbo.utils.IntentUtil;
import com.example.chenguozhen.wcarbo.utils.JSONUitily;
import com.example.chenguozhen.wcarbo.utils.Utility;
import com.example.chenguozhen.wcarbo.wcarbo;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Request;

import static com.example.chenguozhen.wcarbo.Fragment_weibo_profile.CommentListFragment.REQUEST_DATE;
import static com.example.chenguozhen.wcarbo.activity.MainActivity.EXTRA_CLICKBUTTONACTIVITY;

/**
 * Created by chenguozhen on 2018/1/29.
 */

public class CommentChoiceFragment extends DialogFragment implements HttpListener{
    public static final String EXTRA_DATE = "DELTE_CLICKITEM";
    final String url = "https://api.weibo.com/2/comments/destroy.json?source=3867086258";
    private String[] items = null;
    private boolean deleted = false;
    private boolean finsih = false;

    private DialogInterface.OnClickListener onClickListener;

    public static CommentChoiceFragment newInstance(String id,String cid,int type){
        Bundle args = new Bundle();
        args.putString("id",id);
        args.putString("cid",cid);
        args.putInt("Type",type);

        CommentChoiceFragment commentChoiceFragment = new CommentChoiceFragment();
        commentChoiceFragment.setArguments(args);
        return commentChoiceFragment;
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, "CommentChoiceFragment");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String cid = getArguments().getString("cid");
        final String id = getArguments().getString("id");
        int type = getArguments().getInt("Type");
        switch (type){
            case CommentListFragment.byme:
                items = new String[]{"回复这条","显示详情","删除评论"};
                break;
            case CommentListFragment.me:
                items = new String[]{"回复这条","显示详情",};
                break;
        }

        final Oauth2AccessToken tk = AccessTokenKeeper.readAccessToken(wcarbo.getContext());
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                if (which == 2){
                    Request request = Utility.destory_builder(url,tk.getToken(),cid);
                    send(request);

                } else if (1 == which){

                } else if (0 == which){
                    IntentUtil.reply_intent(Fragment_Addweibo.ADDReposts,id,cid);
                }
            }
        };
        builder.setItems(items, onClickListener);
        return builder.create();
    }

    @Override
    public void success(String responseData) {
        try {
            JSONObject jsonObject =new JSONObject(responseData);
            final String text = JSONUitily.parseJSON_Comment(jsonObject).getText();
            if (text !=null){
                deleted = true;
                sendResult(REQUEST_DATE,deleted);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(wcarbo.getContext(),"网络出现问题",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void send(Request request){
        HttpUtil httpUtil = new HttpUtil(this);
        httpUtil.Data_update(request);
    }

    private void sendResult(int resultCode,boolean deleted){
        if (getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE,deleted);

        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
    }
}
