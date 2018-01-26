package com.example.chenguozhen.wcarbo.Fragment_weibo_favouite;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;

import com.example.chenguozhen.wcarbo.Adapter.RecyclerViewAdapter.collection_list_adapter;
import com.example.chenguozhen.wcarbo.Bean.Favorites;
import com.example.chenguozhen.wcarbo.module.base.BaseListFragment;
import com.example.chenguozhen.wcarbo.utils.BaseAsyncTask;
import com.example.chenguozhen.wcarbo.utils.JSONUitily;
import com.example.chenguozhen.wcarbo.utils.Utility;
import com.example.chenguozhen.wcarbo.wcarbo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by chenguozhen on 2018/1/2.
 */

public class Fragment_weibo_favorite extends BaseListFragment{

    private String token;
    private int page = 1;
    private String url ="https://api.weibo.com/2/favorites.json?source=3867086258";
    private List<Favorites.FavoritesBean> favoritesBeans = new ArrayList<Favorites.FavoritesBean>();
    private collection_list_adapter collectionListAdapter;
    private favoriteAsyncTask asyncTask;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = ((wcarbo)getActivity().getApplication()).getToken();

        asyncTask = new favoriteAsyncTask(favoritesBeans,getActivity(),BaseAsyncTask.create);
        asyncTask.execute(url);
    }

    @Override
    protected void SwipeRefresh_Refresh() {
        asyncTask = new favoriteAsyncTask(favoritesBeans,getActivity(),BaseAsyncTask.swipe);
        asyncTask.execute(url);
    }

    @Override
    protected void ScrollListener_LoadMore() {
        asyncTask = new favoriteAsyncTask(favoritesBeans,getActivity(),BaseAsyncTask.scroll);
        asyncTask.execute(url);
    }

    @Override
    public RecyclerView.Adapter adapter() {
        collectionListAdapter = new collection_list_adapter(favoritesBeans,Fragment_weibo_favorite.this);
        return collectionListAdapter;
    }

    private class favoriteAsyncTask extends BaseAsyncTask<Favorites.FavoritesBean>{

        public favoriteAsyncTask(List<Favorites.FavoritesBean> DataList, FragmentActivity fragmentActivity, int type) {
            super(DataList, fragmentActivity, type);
        }

        @Override
        protected void notifyDataAdapterChanged() {
            collectionListAdapter.notifyDataSetChanged();
        }

        @Override
        protected List<Favorites.FavoritesBean> cratevoid(String url, OkHttpClient client) {
            List<Favorites.FavoritesBean> favoritesBeanList = new ArrayList<Favorites.FavoritesBean>();
            Response response = null;
            try {
                page = 1;
                response = client.newCall(Utility.favouite_builder(page,url,token)).execute();
                String responseData = response.body().string();
                Favorites favorites = JSONUitily.favorites(responseData);
                List<Favorites.FavoritesBean> favoritesBeans = favorites.getFavorites();
                if (favoritesBeans != null){
                    favoritesBeanList.addAll(favoritesBeans);
                }
                page++;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return favoritesBeanList;
        }

        @Override
        protected List<Favorites.FavoritesBean> scrollvoid(String url, OkHttpClient client) {
            List<Favorites.FavoritesBean> favoritesBeanList = new ArrayList<Favorites.FavoritesBean>();
            Response response = null;
            try {
                response = client.newCall(Utility.favouite_builder(page,url,token)).execute();
                String responseData = response.body().string();
                Favorites favorites = JSONUitily.favorites(responseData);
                List<Favorites.FavoritesBean> favoritesBeans = favorites.getFavorites();
                if (favoritesBeans != null){
                    favoritesBeanList.addAll(favoritesBeans);
                }
                page++;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return favoritesBeanList;
        }

        @Override
        protected List<Favorites.FavoritesBean> swipevoid(String url, OkHttpClient client) {
            List<Favorites.FavoritesBean> favoritesBeanList = new ArrayList<Favorites.FavoritesBean>();
            Response response = null;
            page = 1;
            try {
                response = client.newCall(Utility.favouite_builder(page,url,token)).execute();
                String responseData = response.body().string();
                Favorites favorites = JSONUitily.favorites(responseData);
                List<Favorites.FavoritesBean> favoritesBeans = favorites.getFavorites();
                if (favoritesBeans != null){
                    favoritesBeanList.addAll(favoritesBeans);
                }
                page++;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return favoritesBeanList;
        }

    }
}

