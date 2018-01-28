package com.example.chenguozhen.wcarbo.Fragment_weibo_hotpost;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;

import com.example.chenguozhen.wcarbo.Adapter.RecyclerViewAdapter.hotweibo_list_adapter;
import com.example.chenguozhen.wcarbo.AsyncTask.BaseAsyncTask;
import com.example.chenguozhen.wcarbo.Bean.JSON.Status;
import com.example.chenguozhen.wcarbo.Bean.Public;
import com.example.chenguozhen.wcarbo.module.base.BaseListFragment;
import com.example.chenguozhen.wcarbo.utils.JSONUitily;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenguozhen on 2017/9/20.
 */

public class Fragment_viewPager_hotpost_hotweibo extends BaseListFragment{

    private List<Status> publicList = new ArrayList<Status>();
    private String url = "https://api.weibo.com/2/statuses/public_timeline.json?source=3867086258";
    private hotweibo_list_adapter hotweiboListAdapter;

    @Override
    public RecyclerView.Adapter adapter() {
        hotweiboListAdapter  = new hotweibo_list_adapter
                (publicList,Fragment_viewPager_hotpost_hotweibo.this);
        return hotweiboListAdapter;
    }

    @Override
    protected void Create_Content(String token) {
        new Publicweibo(publicList,getActivity(),BaseAsyncTask.scroll,token).execute(url);
    }

    @Override
    protected void SwipeRefresh_Refresh(String token) {
        new Publicweibo(publicList,getActivity(),BaseAsyncTask.scroll,token).execute(url);
    }

    @Override
    protected void ScrollListener_LoadMore(String token) {
        new Publicweibo(publicList,getActivity(),BaseAsyncTask.scroll,token).execute(url);
    }

    private class Publicweibo extends BaseAsyncTask<Status>{

        public Publicweibo(List<com.example.chenguozhen.wcarbo.Bean.JSON.Status> DataList, FragmentActivity fragmentActivity, int type, String token) {
            super(DataList, fragmentActivity, type, token);
        }

        @Override
        protected List DataList(String url, OkHttpClient client, int type, String token) {
            List<com.example.chenguozhen.wcarbo.Bean.JSON.Status> statuses = null;
            HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
            builder.addQueryParameter(Oauth2AccessToken.KEY_ACCESS_TOKEN,token);
            Request request = new Request.Builder()
                    .url(builder.build())
                    .build();
            try {
                Response response = client.newCall(request).execute();
                String responseData  = response.body().string();
                Public publics = JSONUitily.publics(responseData);
                statuses = publics.getStatuses();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return statuses;
        }

        @Override
        protected void voidChanged() {
            hotweiboListAdapter.notifyDataSetChanged();
        }
    }

}
