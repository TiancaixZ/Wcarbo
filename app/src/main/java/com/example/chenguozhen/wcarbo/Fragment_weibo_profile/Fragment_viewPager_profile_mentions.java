package com.example.chenguozhen.wcarbo.Fragment_weibo_profile;

import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;

import com.example.chenguozhen.wcarbo.Adapter.RecyclerViewAdapter.comment_list_adapter;
import com.example.chenguozhen.wcarbo.Bean.Comments;
import com.example.chenguozhen.wcarbo.Bean.JSON.Comment;
import com.example.chenguozhen.wcarbo.Interface.RecyclerViewItemClickLisntner;
import com.example.chenguozhen.wcarbo.module.base.BaseListFragment;
import com.example.chenguozhen.wcarbo.AsyncTask.BaseAsyncTask;
import com.example.chenguozhen.wcarbo.utils.JSONUitily;
import com.example.chenguozhen.wcarbo.utils.Utility;
import com.example.chenguozhen.wcarbo.widget.CommentChoiceFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenguozhen on 2018/1/25.
 */

public class Fragment_viewPager_profile_mentions extends CommentListFragment{

    @Override
    protected String url() {
        return  "https://api.weibo.com/2/comments/mentions.json?source=3867086258";
    }

    @Override
    protected int type() {
        return 11;
    }

}
