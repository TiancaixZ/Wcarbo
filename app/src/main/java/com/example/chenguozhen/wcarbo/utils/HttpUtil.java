package com.example.chenguozhen.wcarbo.utils;

import com.example.chenguozhen.wcarbo.Interface.HttpListener;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenguozhen on 2018/1/30.
 */

public class HttpUtil {

    private OkHttpClient client;
    public HttpListener mHttpListener;

    public HttpUtil(HttpListener httpListener){
        this.mHttpListener = httpListener;
    }

    public void Data_update(Request request){
        client = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHttpListener.failed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                mHttpListener.success(responseData);
            }
        });
    }
}
