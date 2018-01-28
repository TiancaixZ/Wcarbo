package com.example.chenguozhen.wcarbo.Fragment_weibo_profile;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;

import com.example.chenguozhen.wcarbo.Adapter.RecyclerViewAdapter.comment_list_adapter;
import com.example.chenguozhen.wcarbo.Bean.Comments;
import com.example.chenguozhen.wcarbo.Bean.JSON.Comment;
import com.example.chenguozhen.wcarbo.module.base.BaseListFragment;
import com.example.chenguozhen.wcarbo.AsyncTask.BaseAsyncTask;
import com.example.chenguozhen.wcarbo.utils.JSONUitily;
import com.example.chenguozhen.wcarbo.utils.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenguozhen on 2018/1/25.
 */

public class Fragment_viewPager_profile_mentions extends BaseListFragment {

    private long max_id = 0;
    private String url = "https://api.weibo.com/2/comments/mentions.json?source=3867086258";
    private List<Comment> commentsList = new ArrayList<Comment>();
    private comment_list_adapter adapter;
    private MentionsAsyncTask asyncTask;

    @Override
    protected void Create_Content(String token) {
        asyncTask = new MentionsAsyncTask(commentsList,getActivity(), BaseAsyncTask.create,token);
        asyncTask.execute(url);
    }

    @Override
    protected void SwipeRefresh_Refresh(String token) {
        asyncTask = new MentionsAsyncTask(commentsList,getActivity(),BaseAsyncTask.swipe,token);
        asyncTask.execute(url);
    }

    @Override
    protected void ScrollListener_LoadMore(String token) {
        if (max_id != 0){
            asyncTask = new MentionsAsyncTask(commentsList,getActivity(),BaseAsyncTask.scroll,token);
            asyncTask.execute(url);
        }

    }

    @Override
    public RecyclerView.Adapter adapter() {
        adapter = new comment_list_adapter(commentsList,Fragment_viewPager_profile_mentions.this);
        return adapter;
    }

    private class MentionsAsyncTask extends BaseAsyncTask<Comment>{
        private long tmp = 0;

        public MentionsAsyncTask(List<Comment> DataList, FragmentActivity fragmentActivity, int type, String token) {
            super(DataList, fragmentActivity, type, token);
        }

        @Override
        protected List DataList(String url, OkHttpClient client, int type, String token) {
            List<Comment> commentList = null;
            if (type == BaseAsyncTask.create || type == BaseAsyncTask.swipe){
                max_id = 0;
            }
            Request request = Utility.byme_builder(url,max_id,token);
            try {
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                Comments comment = JSONUitily.bytomentions(responseData);
                commentList = comment.getComments();
                tmp = comment.getNext_cursor();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return commentList;
        }

        @Override
        protected void voidChanged() {
            max_id = tmp;
            adapter.notifyDataSetChanged();
        }
    }
}
