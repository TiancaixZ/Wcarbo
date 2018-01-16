package com.example.chenguozhen.wcarbo.Fragment_weibo_hotpost;

import android.os.AsyncTask;
import android.os.Bundle;
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
import com.example.chenguozhen.wcarbo.Bean.JSON.Hotweibo;
import com.example.chenguozhen.wcarbo.utils.Utility;
import com.example.chenguozhen.wcarbo.wcarbo;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenguozhen on 2017/9/20.
 */

public class Fragment_viewPager_hotpost_hotweibo extends Fragment{

    private String token;
    private List<Hotweibo> hotweiboList = new ArrayList();
    private String url = "https://api.weibo.com/2/statuses/public_timeline.json?source=3867086258";

    private hotweibo_list_adapter hotweiboListAdapter;

    @BindView(R.id.recyclerview_hotpost_hotweibo) RecyclerView recyclerView_hotpost_hotweibo;
    @BindView(R.id.swiperefresh_hotpost_hotweibo) SwipeRefreshLayout swipeRefreshLayout_hotpost_hotweibo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = ((wcarbo)getActivity().getApplication()).getToken();

        new QueryHotweibo().execute(url);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_hotpost_hotweibo,container,false);

        ButterKnife.bind(this, view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView_hotpost_hotweibo.setLayoutManager(linearLayoutManager);
        hotweiboListAdapter  = new hotweibo_list_adapter
                (hotweiboList,Fragment_viewPager_hotpost_hotweibo.this);
        recyclerView_hotpost_hotweibo.setAdapter(hotweiboListAdapter);
        recyclerView_hotpost_hotweibo.addItemDecoration
                (new DividerItemDecoration(wcarbo.getContext(),DividerItemDecoration.VERTICAL));

        swipeRefreshLayout_hotpost_hotweibo.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new QueryHotweibo().execute(url);
                swipeRefreshLayout_hotpost_hotweibo.setRefreshing(false);
            }
        });

        return view;
    }

    private class QueryHotweibo extends AsyncTask<String,Integer,List>{

        @Override
        protected List doInBackground(String... strings) {
            String url = strings[0];
            Utility.queryHotweibo(url,token);
            List list = Hotweibo_list_add();
            return list;
        }

        @Override
        protected void onPostExecute(List list) {
            super.onPostExecute(list);
            hotweiboList.addAll(list);
            hotweiboListAdapter.notifyDataSetChanged();
        }
    }

    private List<Hotweibo> Hotweibo_list_add(){
        List<Hotweibo> hotweiboList1 = DataSupport.findAll(Hotweibo.class);
        List<Integer> tmp = new ArrayList<Integer>();
        if (hotweiboList1.size() > hotweiboList.size()){
            for (int i = 0; i < hotweiboList.size(); i++) {
                if (hotweiboList.get(i).getIdstr().equals(hotweiboList1.get(i).getIdstr())){
                    tmp.add(i);
                }
            }
            for (int i = 0; i < tmp.size(); i++) {
                hotweiboList1.remove(i);
            }
        }
        Collections.reverse(hotweiboList1);
        return hotweiboList1;
    }

}
