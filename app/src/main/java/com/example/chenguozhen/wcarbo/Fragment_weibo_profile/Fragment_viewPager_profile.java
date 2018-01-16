package com.example.chenguozhen.wcarbo.Fragment_weibo_profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenguozhen.wcarbo.R;

/**
 * Created by chenguozhen on 2017/9/25.
 */

public class Fragment_viewPager_profile extends Fragment {

    private TabLayout profiletabLayout;
    private ViewPager profileviewpager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_viewpager_profile,container,false);

        profiletabLayout = (TabLayout)view.findViewById(R.id.profile_tablayout);

        profileviewpager = (ViewPager)view.findViewById(R.id.framgent_profile_viewpaper);

        profiletabLayout.setupWithViewPager(profileviewpager);


        return view;
    }
}
