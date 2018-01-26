package com.example.chenguozhen.wcarbo.Adapter.RecyclerViewAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.chenguozhen.wcarbo.Adapter.BaseListAdapter;
import com.example.chenguozhen.wcarbo.Bean.Favorites;
import com.example.chenguozhen.wcarbo.Bean.Gson.UsersBean;
import com.example.chenguozhen.wcarbo.Bean.JSON.Status;
import com.example.chenguozhen.wcarbo.Constants;
import com.example.chenguozhen.wcarbo.activity.ClickButtonActivity;
import com.example.chenguozhen.wcarbo.wcarbo;

import java.util.List;

/**
 * Created by chenguozhen on 2018/1/2.
 */

public class collection_list_adapter extends BaseListAdapter<Favorites.FavoritesBean> {

    private List<Favorites.FavoritesBean> mfavoritesBeans;
    private Fragment mFragment;

    public collection_list_adapter(List<Favorites.FavoritesBean> favoritesBeans, Fragment fragment){
        super(favoritesBeans,fragment);
        this.mFragment = fragment;
        this.mfavoritesBeans = favoritesBeans;
    }

    @Override
    protected Status PositionStatus(int position) {
        Status status = mfavoritesBeans.get(position).getStatus();
        return status;
    }

}
