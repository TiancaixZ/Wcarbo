package com.example.chenguozhen.wcarbo.Fragment_weibo_detailed;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenguozhen.wcarbo.Adapter.viewpaperAdapter.detailed_fragment_adapter;
import com.example.chenguozhen.wcarbo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenguozhen on 2018/1/18.
 */

public class Fragment_weibo_detailed extends Fragment{

    @BindView(R.id.detailed_viewpaper) ViewPager detailed_viewpager;
    @BindView(R.id.detailed_tablayout)TabLayout detailed_TableLayout;

    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private List<String> stringList = new ArrayList<String>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_detailed,container,false);
        ButterKnife.bind(this, view);

        detailed_fragment_adapter adapter = new detailed_fragment_adapter(getChildFragmentManager(),fragmentList,stringList);
        detailed_viewpager.setAdapter(adapter);
        detailed_viewpager.setCurrentItem(0);
        detailed_TableLayout.setupWithViewPager(detailed_viewpager);

        return view;
    }

    private void initData(){
        fragmentList.add(new Fragment_weibo_detailed_comments());
        fragmentList.add(new Fragment_weibo_detailed_attitudes());
        fragmentList.add(new Fragment_weibo_detailed_reposts());

        stringList.add("评论");
        stringList.add("赞");
        stringList.add("转发");

    }
}
