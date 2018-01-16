package com.example.chenguozhen.wcarbo.viewpaperAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.chenguozhen.wcarbo.Fragment_weibo_hotpost.Fragment_viewPager_hotpost;
import com.example.chenguozhen.wcarbo.Fragment_weibo_image_cardview.Fragment_ViewPager_Cardview;
import com.example.chenguozhen.wcarbo.Fragment_weibo_profile.Fragment_viewPager_profile;
import com.example.chenguozhen.wcarbo.activity.MainActivity;

/**
 * Created by chenguozhen on 2017/9/25.
 */

public class ViewPaerFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 3;
    private Fragment_ViewPager_Cardview Fragment1 =null;
    private Fragment_viewPager_hotpost Fragment2 = null;
    private Fragment_viewPager_profile Fragment3 = null;

    public ViewPaerFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        Fragment1 = new Fragment_ViewPager_Cardview();
        Fragment2 = new Fragment_viewPager_hotpost();
        Fragment3 = new Fragment_viewPager_profile();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = Fragment1;
                break;
            case MainActivity.PAGE_TWO:
                fragment = Fragment2;
                break;
            case MainActivity.PAGE_THREE:
                fragment = Fragment3;
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
}
