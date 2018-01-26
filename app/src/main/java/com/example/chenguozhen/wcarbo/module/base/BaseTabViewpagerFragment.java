package com.example.chenguozhen.wcarbo.module.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenguozhen.wcarbo.Adapter.viewpaperAdapter.BaseFragmentStatePagerAdapter;
import com.example.chenguozhen.wcarbo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenguozhen on 2018/1/23.
 */

public abstract class BaseTabViewpagerFragment extends Fragment{

    @BindView(R.id.base_tablayout)TabLayout tableLayout;
    @BindView(R.id.base_viewpaper) ViewPager viewPager;

    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private  List<String> stringList = new ArrayList<String>();

    private BaseFragmentStatePagerAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(fragmentList,stringList);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_weibo_tabviewpaper,container,false);
        ButterKnife.bind(this, view);

        adapter = new BaseFragmentStatePagerAdapter(getChildFragmentManager(),fragmentList,stringList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position());
        tableLayout.setupWithViewPager(viewPager);

        return view;
    }

    protected abstract void initData(List<Fragment> fragmentList, List<String> stringList);

    protected abstract int position();
}
