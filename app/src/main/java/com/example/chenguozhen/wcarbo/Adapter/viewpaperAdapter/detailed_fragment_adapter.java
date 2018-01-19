package com.example.chenguozhen.wcarbo.Adapter.viewpaperAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.chenguozhen.wcarbo.Adapter.BaseFragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by chenguozhen on 2018/1/18.
 */

public class detailed_fragment_adapter extends BaseFragmentStatePagerAdapter<Fragment> {

    public detailed_fragment_adapter(FragmentManager fm, List<Fragment> FragmentList, List<String> titles) {
        super(fm, FragmentList, titles);
    }
}
