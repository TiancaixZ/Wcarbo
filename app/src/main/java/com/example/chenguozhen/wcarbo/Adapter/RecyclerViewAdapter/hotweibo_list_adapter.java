package com.example.chenguozhen.wcarbo.Adapter.RecyclerViewAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.chenguozhen.wcarbo.Adapter.BaseListAdapter;
import com.example.chenguozhen.wcarbo.Bean.Gson.UsersBean;
import com.example.chenguozhen.wcarbo.Bean.JSON.Status;
import com.example.chenguozhen.wcarbo.Constants;
import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.RecyclerView.Holder.ImageViewHolder;
import com.example.chenguozhen.wcarbo.RecyclerView.Holder.TextViewHolder;
import com.example.chenguozhen.wcarbo.activity.ClickButtonActivity;
import com.example.chenguozhen.wcarbo.Bean.JSON.Hotweibo;
import com.example.chenguozhen.wcarbo.Bean.JSON.Hotweibo_Pic_urls;
import com.example.chenguozhen.wcarbo.utils.ImageLoader;
import com.example.chenguozhen.wcarbo.wcarbo;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
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
    protected void userpage(int position) {
        super.userpage(position);
        UsersBean usersBean = mPublicList.get(position).getUsersBean();
        Intent intent = new Intent(wcarbo.getContext(),ClickButtonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("com.example.chenguozhen.wcarbo.activity.NAV_FRIENDS", Constants.collection_usepager);
        bundle.putSerializable("UserBean",usersBean);
        intent.putExtras(bundle);
        mFragment.startActivity(intent);
    }

    @Override
    protected void re_userpage(int position) {
        super.re_userpage(position);
        UsersBean usersBean = mPublicList.get(position).getRetweeted_status().getUsersBean();
        Intent intent = new Intent(wcarbo.getContext(),ClickButtonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("com.example.chenguozhen.wcarbo.activity.NAV_FRIENDS", Constants.collection_usepager);
        bundle.putSerializable("UserBean",usersBean);
        intent.putExtras(bundle);
        mFragment.startActivity(intent);
    }

    @Override
    protected Status PositionStatus(int position) {
        Status status = mPublicList.get(position);
        return status;
    }

}
