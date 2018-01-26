package com.example.chenguozhen.wcarbo.Fragment_weibo_profile;

import android.support.v4.app.Fragment;


import com.example.chenguozhen.wcarbo.module.base.BaseTabViewpagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguozhen on 2017/9/25.
 */

public class Fragment_viewPager_profile extends BaseTabViewpagerFragment {

    @Override
    protected void initData(List<Fragment> fragmentList, List<String> stringList) {
        fragmentList.add(new Fragment_viewPager_profile_bymecomments());
        fragmentList.add(new Fragment_viewPager_profile_tomecomments());
        fragmentList.add(new Fragment_viewPager_profile_mentions());

        stringList.add("发出的评论");
        stringList.add("收到的评论");
        stringList.add("@我的评论");
    }

    @Override
    protected int position() {
        return 0;
    }

}
