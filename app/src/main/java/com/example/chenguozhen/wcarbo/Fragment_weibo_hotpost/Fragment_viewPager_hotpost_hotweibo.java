package com.example.chenguozhen.wcarbo.Fragment_weibo_hotpost;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.chenguozhen.wcarbo.Adapter.RecyclerViewAdapter.hotweibo_list_adapter;
import com.example.chenguozhen.wcarbo.Bean.Gson.error;
import com.example.chenguozhen.wcarbo.Bean.JSON.Status;
import com.example.chenguozhen.wcarbo.Bean.Public;
import com.example.chenguozhen.wcarbo.TestData;
import com.example.chenguozhen.wcarbo.module.base.BaseListFragment;
import com.example.chenguozhen.wcarbo.utils.JSONUitily;
import com.example.chenguozhen.wcarbo.wcarbo;
import com.google.gson.Gson;
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

    private String token;
    private List<Status> publicList = new ArrayList<Status>();
    private String url = "https://api.weibo.com/2/statuses/public_timeline.json?source=3867086258";

    private hotweibo_list_adapter hotweiboListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = ((wcarbo)getActivity().getApplication()).getToken();

        new QueryPublicweibo().execute(url);
    }

    private class QueryPublicweibo extends AsyncTask<String,Integer,Boolean> {
        private List<com.example.chenguozhen.wcarbo.Bean.JSON.Status> statusList =
                new ArrayList<com.example.chenguozhen.wcarbo.Bean.JSON.Status>();

        @Override
        protected Boolean doInBackground(String... strings) {
            boolean result = true;
            final String url = strings[0];
            final OkHttpClient client = new OkHttpClient();
            HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
            builder.addQueryParameter(Oauth2AccessToken.KEY_ACCESS_TOKEN,token);
            Request request = new Request.Builder()
                    .url(builder.build())
                    .build();
            try {
                Response response = client.newCall(request).execute();
                String responseData  = response.body().string();
                Public publics = JSONUitily.publics(responseData);
                List<com.example.chenguozhen.wcarbo.Bean.JSON.Status> statuses = publics.getStatuses();
                statusList.addAll(statuses);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean){
                publicList.addAll(statusList);
                hotweiboListAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public RecyclerView.Adapter adapter() {
        hotweiboListAdapter  = new hotweibo_list_adapter
                (publicList,Fragment_viewPager_hotpost_hotweibo.this);
        return hotweiboListAdapter;
    }

    @Override
    protected void SwipeRefresh_Refresh() {
        super.SwipeRefresh_Refresh();
        new QueryPublicweibo().execute(url);
    }

    @Override
    protected void ScrollListener_LoadMore() {
        super.ScrollListener_LoadMore();
    }


}
