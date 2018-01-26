package com.example.chenguozhen.wcarbo.Adapter.RecyclerViewAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.chenguozhen.wcarbo.Adapter.BaseListAdapter;
import com.example.chenguozhen.wcarbo.Bean.Gson.UsersBean;
import com.example.chenguozhen.wcarbo.Bean.JSON.Status;
import com.example.chenguozhen.wcarbo.Constants;
import com.example.chenguozhen.wcarbo.activity.ClickButtonActivity;
import com.example.chenguozhen.wcarbo.wcarbo;

import java.util.List;

/**
 * Created by chenguozhen on 2017/12/28.
 */

public class hotweibo_list_adapter extends BaseListAdapter<Status>{

    private List<Status> mPublicList;
    private Fragment mFragment;

    public hotweibo_list_adapter(List<Status> PublicList, Fragment fragment){
        super(PublicList,fragment);
        this.mPublicList = PublicList;
        this.mFragment = fragment;
    }

    @Override
    protected Status PositionStatus(int position) {
        Status status = mPublicList.get(position);
        return status;
    }

}
