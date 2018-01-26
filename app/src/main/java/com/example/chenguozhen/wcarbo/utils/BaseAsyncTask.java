package com.example.chenguozhen.wcarbo.utils;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.chenguozhen.wcarbo.Bean.Gson.error;
import com.example.chenguozhen.wcarbo.wcarbo;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

/**
 * Created by chenguozhen on 2018/1/24.
 */

public abstract class BaseAsyncTask<T> extends AsyncTask<String,Integer,List>{
    private ProgressDialog dialog;

    public static final int scroll = 0;
    public static final int swipe = 1;
    public static final int create = 2;

    private List<T> mDataList;
    private int mType;

    public BaseAsyncTask(List<T> DataList, FragmentActivity fragmentActivity, int type) {
        this.mDataList = DataList;
        this.mType = type;
        this.dialog = new ProgressDialog(fragmentActivity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mType == create){
            dialog.setMessage("正在加载中");
            dialog.show();
        }
    }

    @Override
    protected List doInBackground(String... strings) {
        final String url = strings[0];
        final OkHttpClient client = new OkHttpClient();
        List<T> data = new ArrayList<T>();
        if (mType == create){
            data.addAll(cratevoid(url,client));
        } else if (mType == scroll){
            data.addAll(scrollvoid(url,client));
        } else if (mType == swipe){
            data.addAll(swipevoid(url,client));
        }
        return data;
    }
    
    @Override
    protected void onPostExecute(List list) {
        super.onPostExecute(list);
        switch (mType){
            case create:
                mDataList.addAll(list);
                dialog.dismiss();
                notifyDataAdapterChanged();
                break;
            case swipe:
                mDataList.clear();
                mDataList.addAll(0,list);
                notifyDataAdapterChanged();
                break;
            case scroll:
                mDataList.addAll(list);
                notifyDataAdapterChanged();
                break;
            default:
                break;
        }
    }

    protected abstract void notifyDataAdapterChanged();

    protected abstract List<T> cratevoid(String url, OkHttpClient client);

    protected abstract List<T> scrollvoid(String url, OkHttpClient client);

    protected abstract List<T> swipevoid(String url, OkHttpClient client);
    
}
