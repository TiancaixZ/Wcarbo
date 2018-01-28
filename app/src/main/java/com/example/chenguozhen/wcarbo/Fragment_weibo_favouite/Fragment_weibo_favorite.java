package com.example.chenguozhen.wcarbo.Fragment_weibo_favouite;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;

import com.example.chenguozhen.wcarbo.Adapter.RecyclerViewAdapter.collection_list_adapter;
import com.example.chenguozhen.wcarbo.Bean.Favorites;
import com.example.chenguozhen.wcarbo.module.base.BaseListFragment;
import com.example.chenguozhen.wcarbo.AsyncTask.BaseAsyncTask;
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

    private int page = 1;
    private String url ="https://api.weibo.com/2/favorites.json?source=3867086258";
    private List<Favorites.FavoritesBean> favoritesBeans = new ArrayList<Favorites.FavoritesBean>();
    private collection_list_adapter collectionListAdapter;
    private favoriteAsyncTask asyncTask;

    @Override
    public RecyclerView.Adapter adapter() {
        collectionListAdapter = new collection_list_adapter(favoritesBeans,Fragment_weibo_favorite.this);
        return collectionListAdapter;
    }

    @Override
    protected void Create_Content(String token) {
        asyncTask = new favoriteAsyncTask(favoritesBeans,getActivity(),BaseAsyncTask.create,token);
        asyncTask.execute(url);
    }

    @Override
    protected void SwipeRefresh_Refresh(String token) {
        asyncTask = new favoriteAsyncTask(favoritesBeans,getActivity(),BaseAsyncTask.create,token);
        asyncTask.execute(url);
    }

    @Override
    protected void ScrollListener_LoadMore(String token) {
        if (page != 1) {
            asyncTask = new favoriteAsyncTask(favoritesBeans, getActivity(), BaseAsyncTask.create, token);
            asyncTask.execute(url);
        }
    }

    private class favoriteAsyncTask extends BaseAsyncTask<Favorites.FavoritesBean>{

        public favoriteAsyncTask(List<Favorites.FavoritesBean> DataList, FragmentActivity fragmentActivity, int type, String token) {
            super(DataList, fragmentActivity, type, token);
        }

        @Override
        protected List DataList(String url, OkHttpClient client, int type, String token) {
            List<Favorites.FavoritesBean> favoritesBeanList = null;
            Response response = null;
            if (type == BaseAsyncTask.create || type == BaseAsyncTask.swipe) {
                page = 1;
            }
            try {
                response = client.newCall(Utility.favouite_builder(page,url,token)).execute();
                String responseData = response.body().string();
                Favorites favorites = JSONUitily.favorites(responseData);
                favoritesBeanList = favorites.getFavorites();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return favoritesBeanList;
        }

        @Override
        protected void voidChanged() {
            page++;
            adapter().notifyDataSetChanged();
        }

    }
}

