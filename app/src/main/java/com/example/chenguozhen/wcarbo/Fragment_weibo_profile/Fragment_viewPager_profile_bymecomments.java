package com.example.chenguozhen.wcarbo.Fragment_weibo_profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.chenguozhen.wcarbo.Adapter.RecyclerViewAdapter.comment_list_adapter;
import com.example.chenguozhen.wcarbo.Bean.Comments;
import com.example.chenguozhen.wcarbo.Bean.JSON.Comment;
import com.example.chenguozhen.wcarbo.module.base.BaseListFragment;
import com.example.chenguozhen.wcarbo.utils.BaseAsyncTask;
import com.example.chenguozhen.wcarbo.utils.JSONUitily;
import com.example.chenguozhen.wcarbo.utils.Utility;
import com.example.chenguozhen.wcarbo.wcarbo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenguozhen on 2018/1/25.
 */

public class Fragment_viewPager_profile_bymecomments extends BaseListFragment{

    private String token;
    private long max_id = 0;
    private String url = "https://api.weibo.com/2/comments/by_me.json?source=3867086258";
    private List<Comment> commentsList = new ArrayList<Comment>();
    private comment_list_adapter adapter;
    private BymeCommentAsyncTask asyncTask;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = ((wcarbo)getActivity().getApplication()).getToken();

        asyncTask = new BymeCommentAsyncTask(commentsList,getActivity(),BaseAsyncTask.create);
        asyncTask.execute(url);
    }

    @Override
    protected void SwipeRefresh_Refresh() {
        asyncTask = new BymeCommentAsyncTask(commentsList,getActivity(),BaseAsyncTask.swipe);
        asyncTask.execute(url);
    }

    @Override
    protected void ScrollListener_LoadMore() {
        asyncTask = new BymeCommentAsyncTask(commentsList,getActivity(),BaseAsyncTask.scroll);
        asyncTask.execute(url);
    }

    @Override
    public RecyclerView.Adapter adapter() {
        adapter = new comment_list_adapter(commentsList,Fragment_viewPager_profile_bymecomments.this);
        return adapter;
    }

    private class BymeCommentAsyncTask extends BaseAsyncTask<Comment>{

        public BymeCommentAsyncTask(List<Comment> DataList, FragmentActivity fragmentActivity, int type) {
            super(DataList, fragmentActivity, type);
        }

        @Override
        protected void notifyDataAdapterChanged() {
            adapter.notifyDataSetChanged();
        }

        @Override
        protected List<Comment> cratevoid(String url, OkHttpClient client) {
            List<Comment> commentList = new ArrayList<Comment>();
            Request request = Utility.byme_builder(url,max_id,token);
            try {
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                Comments comment = JSONUitily.bytomentions(responseData);
                if (comment.getComments() != null){
                    commentList = comment.getComments();
                }
                max_id = comment.getNext_cursor();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return commentList;
        }

        @Override
        protected List<Comment> scrollvoid(String url, OkHttpClient client) {
            List<Comment> commentList = new ArrayList<Comment>();
            Request request = Utility.byme_builder(url,max_id,token);
            try {
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                Comments comment = JSONUitily.bytomentions(responseData);
                if (max_id != 0){
                    commentList.addAll(comment.getComments());
                }
                max_id = comment.getNext_cursor();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return commentList;
        }

        @Override
        protected List<Comment> swipevoid(String url, OkHttpClient client) {
            List<Comment> commentList = new ArrayList<Comment>();
            Request request = Utility.byme_builder(url,max_id,token);
            try {
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                Comments comment = JSONUitily.bytomentions(responseData);
                if (comment.getComments() != null){
                    commentList = comment.getComments();
                }
                max_id = comment.getNext_cursor();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return commentList;
        }

    }
}
