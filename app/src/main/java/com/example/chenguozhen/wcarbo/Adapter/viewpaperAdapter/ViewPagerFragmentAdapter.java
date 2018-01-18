package com.example.chenguozhen.wcarbo.Adapter.viewpaperAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.chenguozhen.wcarbo.Fragment_weibo_hotpost.Fragment_viewPager_hotpost;
import com.example.chenguozhen.wcarbo.Fragment_weibo_hotpost.Fragment_viewPager_hotpost_hottopic;
import com.example.chenguozhen.wcarbo.Fragment_weibo_hotpost.Fragment_viewPager_hotpost_hotweibo;

/**
 * Created by chenguozhen on 2017/12/27.
 */

public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {

    private final int PAGER_COUNT = 2;
    private Fragment_viewPager_hotpost_hotweibo Fragment1 =null;
    private Fragment_viewPager_hotpost_hottopic Fragment2 = null;
    private String[] titles = new String[]{"热门微博", "热门话题"};

    public ViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        Fragment1 = new Fragment_viewPager_hotpost_hotweibo();
        Fragment2 = new Fragment_viewPager_hotpost_hottopic();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case Fragment_viewPager_hotpost.Hotpost_hotweibo:
                fragment = Fragment1;
                break;
            case Fragment_viewPager_hotpost.Hotpost_hottopic:
                fragment = Fragment2;
                break;
            default:
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
