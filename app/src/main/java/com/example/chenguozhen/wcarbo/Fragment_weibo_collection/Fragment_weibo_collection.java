package com.example.chenguozhen.wcarbo.Fragment_weibo_collection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenguozhen.wcarbo.Adapter.RecyclerViewAdapter.collection_list_adapter;
import com.example.chenguozhen.wcarbo.Bean.Favorites;
import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.utils.JSONUitily;
import com.example.chenguozhen.wcarbo.utils.Utility;
import com.example.chenguozhen.wcarbo.wcarbo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.chenguozhen.wcarbo.utils.Utility.Pagecount;

/**
 * Created by chenguozhen on 2018/1/2.
 */

public class Fragment_weibo_collection extends Fragment{

    private String token;
    private String url ="https://api.weibo.com/2/favorites.json?source=3867086258";
    private List<Favorites.FavoritesBean> favoritesBeans = new ArrayList<Favorites.FavoritesBean>();
    private collection_list_adapter collectionListAdapter;
    private weiboAsyncTask weiboAsyncTask;


    @BindView(R.id.swiperefresh_collection_list) SwipeRefreshLayout swipeRefreshLayout_collection_list;
    @BindView(R.id.collection_list) RecyclerView recyclerView_collection_list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = ((wcarbo)getActivity().getApplication()).getToken();
        weiboAsyncTask = new weiboAsyncTask();
        weiboAsyncTask.execute(url);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_collection,container,false);
        ButterKnife.bind(this, view);

        recyclerView_collection_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        collectionListAdapter = new collection_list_adapter(favoritesBeans,Fragment_weibo_collection.this);
        recyclerView_collection_list.setAdapter(collectionListAdapter);

        return view;
    }

    public class weiboAsyncTask extends AsyncTask<String,Integer,Boolean> {

        private List<Favorites.FavoritesBean> favoritesBeanList = new ArrayList<Favorites.FavoritesBean>();

        @Override
        protected Boolean doInBackground(String... strings) {
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
                Favorites favorites = JSONUitily.parseJSON_favorites(responseData);
                List<Favorites.FavoritesBean> favoritesBeans = favorites.getFavorites();
                favoritesBeanList.addAll(favoritesBeans);
                final int count  = Pagecount(responseData);
                if (count >= 2){
                    for (int i = 2; i <= count; i++) {
                        Response response1 = client.newCall(Utility.builder(i,url,token)).execute();
                        String responseData1 = response1.body().string();
                        Favorites favorites1 = JSONUitily.parseJSON_favorites(responseData1);
                        List<Favorites.FavoritesBean> favoritesBeans1 = favorites1.getFavorites();
                        favoritesBeanList.addAll(favoritesBeans1);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean == true){
                favoritesBeans.addAll(favoritesBeanList);
                collectionListAdapter.notifyDataSetChanged();
            }
        }
    }

}

