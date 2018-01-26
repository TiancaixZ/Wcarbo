package com.example.chenguozhen.wcarbo.Fragment_weibo_detailed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.chenguozhen.wcarbo.module.base.BaseTabViewpagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguozhen on 2018/1/18.
 */

public class Fragment_weibo_detailed extends BaseTabViewpagerFragment{

    private int currentiem;
    private String detial_idstr;

    public static final int Comments = 1;
    public static final int Attitudes = 2;
    public static final int Reposts = 0;

    public static Fragment_weibo_detailed newInstance(int currentitem,String detial_idstr){
        Bundle args = new Bundle();
        args.putInt("position",currentitem);
        args.putString("detail",detial_idstr);
        Log.d("comment_detailed",detial_idstr);
        Fragment_weibo_detailed fragment_weibo_detailed = new Fragment_weibo_detailed();
        fragment_weibo_detailed.setArguments(args);
        return fragment_weibo_detailed;
    }

    @Override
    protected void initData(List<Fragment> fragmentList, List<String> stringList) {
        detial_idstr = getArguments().getString("detail");
        currentiem = getArguments().getInt("position");
        fragmentList.add(Fragment_weibo_detailed_reposts.newInstance(detial_idstr));
        Log.d("comment_addlist",detial_idstr);
        fragmentList.add(Fragment_weibo_detailed_comments.newInstance(detial_idstr));
        fragmentList.add(new Fragment_weibo_detailed_attitudes());

        stringList.add("转发");
        stringList.add("评论");
        stringList.add("赞");
    }

    @Override
    protected int position() {
        return currentiem;
    }


}
