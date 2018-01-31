package com.example.chenguozhen.wcarbo.utils;

import android.support.v4.app.Fragment;

import com.example.chenguozhen.wcarbo.Fragment_add.Fragment_Addweibo;

import com.example.chenguozhen.wcarbo.Fragment_weibo_favouite.Fragment_weibo_favorite;
import com.example.chenguozhen.wcarbo.Fragment_weibo_detailed.Fragment_weibo_detailed;
import com.example.chenguozhen.wcarbo.Fragment_weibo_friends.Fragment_nav_friends;
import com.example.chenguozhen.wcarbo.Fragment_weibo_userpager.Fragment_userpager;
import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.activity.ClickButtonActivity;

import static com.example.chenguozhen.wcarbo.activity.ClickButtonActivity.Fragment_UserBean;
import static com.example.chenguozhen.wcarbo.activity.ClickButtonActivity.cid;

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
            case Fragment_UserBean:
                fragment = Fragment_userpager.newInstance(ClickButtonActivity.usersBean);
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
            case Fragment_Addweibo.ADDReposts:
                fragment = Fragment_Addweibo.newInstance(ClickButtonActivity.detail_idstr,
                        ClickButtonActivity.cid,Fragment_Addweibo.ADDReposts);
                ClickButtonActivity.title = "写评论";
                break;
            case Fragment_Addweibo.AddReply:
                fragment = Fragment_Addweibo.newInstance(ClickButtonActivity.detail_idstr,
                        ClickButtonActivity.cid,Fragment_Addweibo.AddReply);
                ClickButtonActivity.title = "回评论";
                break;
            default:
                break;
        }
        return fragment;
    }



}
