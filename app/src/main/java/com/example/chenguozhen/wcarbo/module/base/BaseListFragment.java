package com.example.chenguozhen.wcarbo.module.base;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.chenguozhen.wcarbo.Adapter.EndlessRecyclerViewScrollListener;
import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.wcarbo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;

/**
 * Created by chenguozhen on 2018/1/19.
 */

public abstract class BaseListFragment extends Fragment{

    @BindView(R.id.Swiperefresh) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.Recyclerview) RecyclerView recyclerView;

    private EndlessRecyclerViewScrollListener scrollListener;
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener;

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
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                ScrollListener_LoadMore();
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
        onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SwipeRefresh_Refresh();
                swipeRefreshLayout.setRefreshing(false);
            }
        };
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);

        return view;
    }

    protected void SwipeRefresh_Refresh(){
    }

    protected void ScrollListener_LoadMore(){
    }

    public abstract RecyclerView.Adapter adapter();

}
