package com.example.chenguozhen.wcarbo.Fragment_weibo_hotpost;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.Adapter.viewpaperAdapter.ViewPagerFragmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenguozhen on 2017/9/20.
 */

public class Fragment_viewPager_hotpost extends Fragment {

    @BindView(R.id.tablayout)TabLayout Hotpost_TableLayout;
    @BindView(R.id.framgent_hotpost_viewpaper) ViewPager hotpostview;

    public static final int Hotpost_hotweibo = 0;
    public static final int Hotpost_hottopic = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_hotpost,container,false);
        ButterKnife.bind(this, view);

        ViewPagerFragmentAdapter  adapter = new ViewPagerFragmentAdapter(getChildFragmentManager());
        hotpostview.setAdapter(adapter);
        hotpostview.setCurrentItem(Hotpost_hotweibo);
        Hotpost_TableLayout.setupWithViewPager(hotpostview);

        return view;
    }

}
