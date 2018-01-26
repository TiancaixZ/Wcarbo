package com.example.chenguozhen.wcarbo.Fragment_weibo_hotpost;

import android.support.v4.app.Fragment;

import com.example.chenguozhen.wcarbo.module.base.BaseTabViewpagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguozhen on 2017/9/20.
 */

public class Fragment_viewPager_hotpost extends BaseTabViewpagerFragment {

    @Override
    protected void initData(List<Fragment> fragmentList, List<String> stringList) {
        fragmentList.add(new Fragment_viewPager_hotpost_hotweibo());
        fragmentList.add(new Fragment_viewPager_hotpost_hottopic());

        stringList.add("热门微博");
        stringList.add("热门话题");
    }

    @Override
    protected int position() {
        return 0;
    }

}
