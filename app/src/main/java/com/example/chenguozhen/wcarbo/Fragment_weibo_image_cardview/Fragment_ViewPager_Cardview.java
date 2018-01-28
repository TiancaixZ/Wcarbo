package com.example.chenguozhen.wcarbo.Fragment_weibo_image_cardview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenguozhen.wcarbo.Adapter.EndlessRecyclerViewScrollListener;
import com.example.chenguozhen.wcarbo.Adapter.RecyclerViewAdapter.timeline_list_adapter;
import com.example.chenguozhen.wcarbo.Bean.JSON.Status;
import com.example.chenguozhen.wcarbo.Bean.Statuses;
import com.example.chenguozhen.wcarbo.Fragment_add.Fragment_Addweibo;
import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.activity.ClickButtonActivity;
import com.example.chenguozhen.wcarbo.data.TimeLIneData;
import com.example.chenguozhen.wcarbo.AsyncTask.BaseAsyncTask;
import com.example.chenguozhen.wcarbo.utils.JSONUitily;
import com.example.chenguozhen.wcarbo.utils.Utility;
import com.example.chenguozhen.wcarbo.wcarbo;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Response;

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
    private timeline_list_adapter adapter;

    private String token;
    private long max_id = 0;
    private long since_id = 0;
    private String url = "https://api.weibo.com/2/statuses/home_timeline.json?source=3867086258";
    private List<Status> Timelist = new ArrayList<Status>();
    private TimelineAsyncTask asyncTask;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Oauth2AccessToken tk = AccessTokenKeeper.readAccessToken(getContext());
        token = tk.getToken();
        if (token != null){
            asyncTask = new TimelineAsyncTask(Timelist,getActivity(),BaseAsyncTask.create,token);
            asyncTask.execute(url);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_framgent_cardview,container,false);

        ButterKnife.bind(this, view);

        adapter = new timeline_list_adapter(Timelist,Fragment_ViewPager_Cardview.this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration
                (new DividerItemDecoration(wcarbo.getContext(),DividerItemDecoration.VERTICAL));

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                if (token != null) {
                    ScrollListener_LoadMore();
                }
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
        onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (token != null) {
                    SwipeRefresh_Refresh();
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        };
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ClickButtonActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(EXTRA_CLICKBUTTONACTIVITY, Fragment_Addweibo.Addweibo);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void  SwipeRefresh_Refresh(){
        asyncTask = new TimelineAsyncTask(Timelist,getActivity(),BaseAsyncTask.swipe,token);
        asyncTask.execute(url);
    }

    private void ScrollListener_LoadMore(){
        if (max_id != 0) {
            asyncTask = new TimelineAsyncTask(Timelist, getActivity(), BaseAsyncTask.scroll, token);
            asyncTask.execute(url);
        }
    }

    private class TimelineAsyncTask extends BaseAsyncTask<Status> {

        public TimelineAsyncTask(List<com.example.chenguozhen.wcarbo.Bean.JSON.Status> DataList, FragmentActivity fragmentActivity, int type, String token) {
            super(DataList, fragmentActivity, type, token);
        }

        @Override
        protected List DataList(String url, OkHttpClient client, int type, String token) {
            List<com.example.chenguozhen.wcarbo.Bean.JSON.Status> statusList = null;
            Response response = null;
            if (type == BaseAsyncTask.swipe || type == BaseAsyncTask.create){
                max_id = 0;
                since_id = 0;
            }
            try {
                response = client.newCall(Utility.timeline_builder(url,max_id,since_id,token)).execute();
                String responseData = response.body().string();
                Statuses statuses = JSONUitily.statuses(responseData);
                statusList = statuses.getStatusList();
                since_id= statuses.getNext_cursor();
                max_id = statuses.getMax_id();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return statusList;
        }

        @Override
        protected void voidChanged() {
            adapter.notifyDataSetChanged();
        }
    }

}
