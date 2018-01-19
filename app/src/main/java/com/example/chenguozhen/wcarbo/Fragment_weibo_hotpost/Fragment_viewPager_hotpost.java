package com.example.chenguozhen.wcarbo.Fragment_weibo_hotpost;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenguozhen.wcarbo.Adapter.viewpaperAdapter.hotpost_fragment_adapter;
import com.example.chenguozhen.wcarbo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenguozhen on 2017/9/20.
 */

public class Fragment_viewPager_hotpost extends Fragment {

    @BindView(R.id.tablayout)TabLayout Hotpost_TableLayout;
    @BindView(R.id.framgent_hotpost_viewpaper) ViewPager hotpostview;

    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private List<String> stringList = new ArrayList<String>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_hotpost,container,false);
        ButterKnife.bind(this, view);

        hotpost_fragment_adapter adapter = new hotpost_fragment_adapter(getChildFragmentManager(),fragmentList,stringList);
        hotpostview.setAdapter(adapter);
        hotpostview.setCurrentItem(0);
        Hotpost_TableLayout.setupWithViewPager(hotpostview);

        return view;
    }

    /** **/
    private void initData(){
        stringList.add("热门微博");
        stringList.add("热门话题");

        fragmentList.add(new Fragment_viewPager_hotpost_hotweibo());
        fragmentList.add(new Fragment_viewPager_hotpost_hottopic());
    }

}
