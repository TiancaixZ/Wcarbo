package com.example.chenguozhen.wcarbo.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.chenguozhen.wcarbo.Constants;
import com.example.chenguozhen.wcarbo.Fragment_weibo_collection.Fragment_weibo_collection;
import com.example.chenguozhen.wcarbo.Fragment_weibo_friends.Fragment_nav_friends;
import com.example.chenguozhen.wcarbo.Fragment_weibo_userpager.Fragment_userpager;
import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.activity.ClickButtonActivity;


/**
 * Created by chenguozhen on 2017/12/3.
 */

public class FragmentFactory {

    /**
     *
     * @param resId
     * @return
     */
    public static Fragment CreateByNav(int resId){
        Fragment fragment = null;
        switch (resId){
            case R.id.nav_item_friends:
                fragment = new Fragment_nav_friends();
                break;
            case R.id.nav_item_profile:
                fragment = new Fragment_userpager();
                break;
            case R.id.nav_item_Collection:
                fragment = new Fragment_weibo_collection();
                break;
            case R.id.friends_list_name:
                fragment = new Fragment_userpager();
                Bundle bundle = new Bundle();
                bundle.putSerializable("fridnes", ClickButtonActivity.frindes);
                fragment.setArguments(bundle);
                break;
            case R.id.weibo_image_avatar:
                fragment = new Fragment_userpager();
                Bundle bundle_hotweibo_avatar = new Bundle();
                bundle_hotweibo_avatar.putSerializable("hotweibo",ClickButtonActivity.hotweibo);
                fragment.setArguments(bundle_hotweibo_avatar);
                break;
            case Constants.collection_usepager:
                fragment = new Fragment_userpager();
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("userbean",ClickButtonActivity.usersBean);
                fragment.setArguments(bundle1);
            default:
                break;

        }
        return fragment;
    }

}
