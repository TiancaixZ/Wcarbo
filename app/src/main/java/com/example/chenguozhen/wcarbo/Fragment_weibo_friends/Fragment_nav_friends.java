package com.example.chenguozhen.wcarbo.Fragment_weibo_friends;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.Bean.JSON.Frindes;
import com.example.chenguozhen.wcarbo.utils.Utility;
import com.example.chenguozhen.wcarbo.wcarbo;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenguozhen on 2017/11/23.
 */

public class Fragment_nav_friends extends Fragment {

    private String token;
    private String uId;
    private List<Frindes> Frindeslist = new ArrayList();
    private String url = "https://api.weibo.com/2/friendships/friends.json?source=3867086258";

    @BindView(R.id.firends_list) RecyclerView firends_list;
    @BindView(R.id.swiperefresh_frindeslist) SwipeRefreshLayout swipeRefresh_frindeslist;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = ((wcarbo)getActivity().getApplication()).getToken();
        uId = ((wcarbo)getActivity().getApplication()).getUid();
        new QueryFrindes().execute(url);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_firends,container,false);
        ButterKnife.bind(this, view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        firends_list.setLayoutManager(linearLayoutManager);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(getActivity(),
                linearLayoutManager.getOrientation());
        mDividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.friends_list_recyclver_divider_line));
        firends_list.addItemDecoration(mDividerItemDecoration);

        setupAdapter();

        swipeRefresh_frindeslist.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh_frindeslist.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new QueryFrindes().execute(url);
                swipeRefresh_frindeslist.setRefreshing(false);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 异步更新获取最新的关注
     */
    private class QueryFrindes extends AsyncTask<String,Integer,List> {

        @Override
        protected List doInBackground(String... strings) {
            String url = strings[0];
            Utility.queryfriends(url,token,uId, Utility.QueryFriends);
            List list = Frindes_list();
            return list;
        }

        @Override
        protected void onPostExecute(List list) {
            super.onPostExecute(list);
            Frindeslist = list;
            setupAdapter();
        }
    }

    /**设置recyclelist.setAdapter**/
    private void setupAdapter(){
        firends_list.setAdapter(new friends_list_adapter(Frindeslist,Fragment_nav_friends.this));
    }

    /**
     * 查询Fridnes数据库，生成frindeslist。
     * @return
     */
    private List<Frindes> Frindes_list(){
        List<Frindes> frindesDatatLIst =DataSupport.findAll(Frindes.class);
        return frindesDatatLIst;
    }
}
