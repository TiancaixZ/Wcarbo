package com.example.chenguozhen.wcarbo.module.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.wcarbo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenguozhen on 2018/1/19.
 */

public abstract class BaseListFragment extends Fragment{

    @BindView(R.id.Swiperefresh) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.Recyclerview) RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_baselistfragment,container,false);

        ButterKnife.bind(this, view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration
                (new DividerItemDecoration(wcarbo.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter());
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SwipeRefresh_Refresh();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        ButterKnife.bind(this, view);

        return view;
    }

    protected void SwipeRefresh_Refresh(){
    }

    protected RecyclerView.Adapter adapter(){
        return null;
    }

}
