package com.example.chenguozhen.wcarbo.Fragment_weibo_image_cardview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenguozhen.wcarbo.R;

import java.util.ArrayList;

/**
 * Created by chenguozhen on 2017/9/13.
 */

public class Fragment_ViewPager_Cardview extends Fragment{

    private RecyclerView WeiboContentList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_framgent_cardview,container,false);

        return v;
    }


}
