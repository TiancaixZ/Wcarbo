package com.example.chenguozhen.wcarbo.Fragment_weibo_collection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenguozhen.wcarbo.Bean.Favorites;
import com.example.chenguozhen.wcarbo.Bean.JSON.Collectionweibo;
import com.example.chenguozhen.wcarbo.Bean.JSON.Collectionweibo_Pic_urls;
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
    private List<Collectionweibo> collectionweiboList = new ArrayList<>();
    private List<Collectionweibo_Pic_urls> collectionweibo_pic_urls = new ArrayList<>();
    private collection_list_adapter collectionListAdapter;
    private weiboAsyncTask weiboAsyncTask;

    private List<Favorites.FavoritesBean> favoritesBeans = new ArrayList<Favorites.FavoritesBean>();

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
        collectionListAdapter = new collection_list_adapter
                (collectionweiboList,collectionweibo_pic_urls,Fragment_weibo_collection.this);
        recyclerView_collection_list.setAdapter(collectionListAdapter);
       // collectionListAdapter.notifyDataSetChanged();


        return view;
    }

    public class weiboAsyncTask extends AsyncTask<String,Integer,Boolean> {

        private List<Collectionweibo> collectionweibos = new ArrayList<Collectionweibo>();
        private List<Collectionweibo_Pic_urls> picUrls = new ArrayList<Collectionweibo_Pic_urls>();

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
                //List<Collectionweibo> collectionweibo = JSONUitily.collectionweiboList(responseData);
                //List<Collectionweibo_Pic_urls> pic_urls = JSONUitily.collectionweibo_pic_urlsList(responseData);
                //collectionweibos.addAll(collectionweibo);
                //picUrls.addAll(pic_urls);

                Favorites favorites = JSONUitily.parseJSON_favorites(responseData);
                List<Favorites.FavoritesBean> favoritesBeans = favorites.getFavorites();
                favoritesBeanList.addAll(favoritesBeans);

                final int count  = Pagecount(responseData);
                if (count >= 2){
                    for (int i = 2; i <= count; i++) {
                        Response response1 = client.newCall(Utility.builder(i,url,token)).execute();
                        String responseData1 = response1.body().string();
                        //List<Collectionweibo> collectionweibo1 = JSONUitily.collectionweiboList(responseData1);
                        //List<Collectionweibo_Pic_urls> pic_urls1 = JSONUitily.collectionweibo_pic_urlsList(responseData1);
                        //collectionweibos.addAll(collectionweibo1);
                        //picUrls.addAll(pic_urls1);

                        Favorites favorites1 = JSONUitily.parseJSON_favorites(responseData);
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
                //collectionweiboList.addAll(collectionweibos);
                //collectionweibo_pic_urls.addAll(picUrls);

                favoritesBeans.addAll(favoritesBeanList);

                collectionListAdapter.notifyDataSetChanged();
            }
        }
    }

}

