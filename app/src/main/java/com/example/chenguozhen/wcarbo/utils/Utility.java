package com.example.chenguozhen.wcarbo.utils;

import android.util.Log;

import com.example.chenguozhen.wcarbo.Bean.Gson.test;
import com.example.chenguozhen.wcarbo.data.FriendsData;
import com.google.gson.Gson;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenguozhen on 2017/11/22.
 */

public class Utility {

    public static final int QueryFriends = 0;
    public static final int QueryUse = 1;

    /**
     * 查询frineds api(https://api.weibo.com/2/friendships/friends.json)并解析
     * @param url
     * @param access_token
     * @param uId
     */
    public static void queryfriends(String url , String access_token , String uId, final int Query) {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        builder.addQueryParameter(Oauth2AccessToken.KEY_ACCESS_TOKEN,access_token)
                .addQueryParameter(Oauth2AccessToken.KEY_UID,uId);

        Request request = new Request.Builder()
                .url(builder.build())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("friends send","干他妈的获取失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                switch (Query){
                    case QueryFriends:
                        Log.d("json",responseData);
                        FriendsData friendsData = new FriendsData();
                        friendsData.onLoad(JSONUitily.frindesList(responseData));
                        break;
                    case QueryUse:
                        JSONUitily.paerseJSON_USEPAGER(responseData);
                        break;
                }
            }
        });

    }

    public static int Pagecount(String jsondata){
        int count = 1;
        int once = 20;
            Gson gson = new Gson();
            test test =gson.fromJson(jsondata,test.class);
            int total_number = test.getTotal_number();
            if (total_number % once == 0){
                count = total_number / once;
            } else {
                count = total_number / once + 1;

            }

        return count;
    }

    public static Request builder(int page,String url,String access_token){
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        builder.addQueryParameter(Oauth2AccessToken.KEY_ACCESS_TOKEN,access_token);
        builder.addQueryParameter("page", String.valueOf(page));
        Request request = new Request.Builder()
                .url(builder.build())
                .build();
        return request;
    }

}
