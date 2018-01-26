package com.example.chenguozhen.wcarbo.Fragment_weibo_image_cardview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenguozhen.wcarbo.Adapter.EndlessRecyclerViewScrollListener;
import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.activity.ClickButtonActivity;
import com.example.chenguozhen.wcarbo.activity.MainActivity;
import com.example.chenguozhen.wcarbo.wcarbo;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.chenguozhen.wcarbo.activity.MainActivity.EXTRA_CLICKBUTTONACTIVITY;


/**
 * Created by chenguozhen on 2017/9/13.
 */

public class Fragment_ViewPager_Cardview extends Fragment{

   @BindView(R.id.Swiperefresh) SwipeRefreshLayout swipeRefreshLayout;
   @BindView(R.id.Recyclerview) RecyclerView recyclerView;
   @BindView(R.id.fab) FloatingActionButton floatingActionButton;

    private EndlessRecyclerViewScrollListener scrollListener;
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_framgent_cardview,container,false);

        ButterKnife.bind(this, view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration
                (new DividerItemDecoration(wcarbo.getContext(),DividerItemDecoration.VERTICAL));

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

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ClickButtonActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(EXTRA_CLICKBUTTONACTIVITY, );
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;
    }

    private void  SwipeRefresh_Refresh(){

    }

    private void ScrollListener_LoadMore(){

    }

}
