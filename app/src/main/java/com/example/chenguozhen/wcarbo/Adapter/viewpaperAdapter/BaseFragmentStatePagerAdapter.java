package com.example.chenguozhen.wcarbo.Adapter.viewpaperAdapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by chenguozhen on 2018/1/18.
 */

public class BaseFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> mFragmentList;
    List<String> mTitles;

    public BaseFragmentStatePagerAdapter(FragmentManager fm,List<Fragment> FragmentList,List<String> titles) {
        super(fm);
        this.mFragmentList = FragmentList;
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
