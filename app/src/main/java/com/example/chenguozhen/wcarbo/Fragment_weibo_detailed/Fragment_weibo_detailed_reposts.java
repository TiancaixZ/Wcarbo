package com.example.chenguozhen.wcarbo.Fragment_weibo_detailed;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;

import com.example.chenguozhen.wcarbo.Adapter.RecyclerViewAdapter.repost_list_adapter;
import com.example.chenguozhen.wcarbo.Bean.JSON.Status;
import com.example.chenguozhen.wcarbo.Bean.Repost;
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
 * Created by chenguozhen on 2018/1/18.
 */

public class Fragment_weibo_detailed_reposts extends BaseListFragment {

    private String detail_idstr;
    private long max_id = 0;
    private String url = "https://api.weibo.com/2/statuses/repost_timeline.json?source=3867086258";
    private List<Status> repostList = new ArrayList<Status>();
    private repost_list_adapter adapter;
    private RepostsAsyncTask asyncTask;

    public static Fragment_weibo_detailed_reposts newInstance(String detial_idstr){
        Bundle args = new Bundle();
        args.putString("detail",detial_idstr);

        Fragment_weibo_detailed_reposts fragment_weibo_detailed_reposts = new Fragment_weibo_detailed_reposts();
        fragment_weibo_detailed_reposts.setArguments(args);
        return fragment_weibo_detailed_reposts;
    }

    @Override
    public RecyclerView.Adapter adapter() {
        adapter = new repost_list_adapter(repostList,Fragment_weibo_detailed_reposts.this);
        return adapter;
    }

    @Override
    protected void Create_Content(String token) {
        detail_idstr = getArguments().getString("detail");
        asyncTask = new RepostsAsyncTask(repostList,getActivity(),BaseAsyncTask.create,token);
        asyncTask.execute(url);
    }

    @Override
    protected void SwipeRefresh_Refresh(String token) {
        if (max_id != 0) {
            asyncTask = new RepostsAsyncTask(repostList, getActivity(), BaseAsyncTask.swipe, token);
            asyncTask.execute(url);
        }
    }

    @Override
    protected void ScrollListener_LoadMore(String token) {
        asyncTask = new RepostsAsyncTask(repostList,getActivity(),BaseAsyncTask.scroll,token);
        asyncTask.execute(url);
    }

    private class RepostsAsyncTask extends BaseAsyncTask<Status> {

        private long tmp = 0;

        public RepostsAsyncTask(List<com.example.chenguozhen.wcarbo.Bean.JSON.Status> DataList, FragmentActivity fragmentActivity, int type, String token) {
            super(DataList, fragmentActivity, type, token);
        }

        @Override
        protected List DataList(String url, OkHttpClient client, int type, String token) {
            if (type == BaseAsyncTask.create || type == BaseAsyncTask.swipe){
                max_id = 0;
            }
            List<com.example.chenguozhen.wcarbo.Bean.JSON.Status> statusList = null;
            Request request = Utility.budiler(url,max_id,token,detail_idstr);
            try {
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                Repost repost = JSONUitily.repost(responseData);
                statusList = repost.getReposts();
                tmp = repost.getNext_cursor();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return statusList;
        }

        @Override
        protected void voidChanged() {
            max_id = tmp;
            adapter.notifyDataSetChanged();
        }
    }
}
