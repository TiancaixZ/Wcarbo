package com.example.chenguozhen.wcarbo.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.chenguozhen.wcarbo.Constants;
import com.example.chenguozhen.wcarbo.Fragment_add.Fragment_Addweibo;
import com.example.chenguozhen.wcarbo.Fragment_weibo_detailed.Fragment_weibo_detailed_reposts;
import com.example.chenguozhen.wcarbo.Fragment_weibo_favouite.Fragment_weibo_favorite;
import com.example.chenguozhen.wcarbo.Fragment_weibo_detailed.Fragment_weibo_detailed;
import com.example.chenguozhen.wcarbo.Fragment_weibo_friends.Fragment_nav_friends;
import com.example.chenguozhen.wcarbo.Fragment_weibo_userpager.Fragment_userpager;
import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.activity.ClickButtonActivity;

import static com.example.chenguozhen.wcarbo.Fragment_add.Fragment_Addweibo.Addweibo;


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
                ClickButtonActivity.title = "朋友";
                break;
            case R.id.nav_item_profile:
                fragment = new Fragment_userpager();
                ClickButtonActivity.title = "主页";
                break;
            case R.id.nav_item_Collection:
                fragment = new Fragment_weibo_favorite();
                ClickButtonActivity.title = "收藏";
                break;
            case R.id.friends_list_name:
                fragment = new Fragment_userpager();
                Bundle bundle = new Bundle();
                bundle.putSerializable("fridnes", ClickButtonActivity.frindes);
                fragment.setArguments(bundle);
                ClickButtonActivity.title = "主页";
                break;
            case Constants.collection_usepager:
                fragment = new Fragment_userpager();
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("userbean",ClickButtonActivity.usersBean);
                fragment.setArguments(bundle1);
                ClickButtonActivity.title = "主页";
                break;
            case Fragment_weibo_detailed.Comments:
                fragment = Fragment_weibo_detailed.newInstance
                        (Fragment_weibo_detailed.Comments,ClickButtonActivity.detail_idstr);
                ClickButtonActivity.title = "评论";
                break;
            case Fragment_weibo_detailed.Reposts:
                fragment = Fragment_weibo_detailed.newInstance
                        (Fragment_weibo_detailed.Reposts,ClickButtonActivity.detail_idstr);
                ClickButtonActivity.title = "转发";
                break;
            case Fragment_Addweibo.Addweibo:
                fragment = new Fragment_Addweibo();
                ClickButtonActivity.title = "发微博";
                break;
            default:
                break;
        }
        return fragment;
    }



}
