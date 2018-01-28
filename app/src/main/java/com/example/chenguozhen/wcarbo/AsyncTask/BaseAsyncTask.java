package com.example.chenguozhen.wcarbo.AsyncTask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.chenguozhen.wcarbo.Bean.Gson.error;
import com.example.chenguozhen.wcarbo.wcarbo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenguozhen on 2018/1/24.
 */

public abstract class BaseAsyncTask<T> extends AsyncTask<String,Integer,List>{
    private ProgressDialog dialog;

    public static final int scroll = 0;
    public static final int swipe = 1;
    public static final int create = 2;

    private List<T> mDataList;
    private String mToken;
    private int mType;

    public BaseAsyncTask(List<T> DataList, FragmentActivity fragmentActivity,int type,String token) {
        this.mDataList = DataList;
        this.mType = type;
        this.mToken = token;
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
        final OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        return DataList(url,client,mType,mToken);
    }

    @Override
    protected void onPostExecute(List list) {
        super.onPostExecute(list);
        if (list == null){
            dialog.dismiss();
            Toast.makeText(wcarbo.getContext(),"发生错误无法刷新",Toast.LENGTH_SHORT).show();
        } else {
            switch (mType){
                case create:
                    mDataList.addAll(list);
                    dialog.dismiss();
                    voidChanged();
                    break;
                case swipe:
                    mDataList.clear();
                    mDataList.addAll(0,list);
                    voidChanged();
                    break;
                case scroll:
                    mDataList.addAll(list);
                    voidChanged();
                    break;
                default:
                    break;
            }
        }
    }

    protected abstract List DataList(String url, OkHttpClient client, int type,String token);
    protected abstract void voidChanged();

}
