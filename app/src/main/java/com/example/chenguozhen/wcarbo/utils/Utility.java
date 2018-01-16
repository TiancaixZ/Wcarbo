package com.example.chenguozhen.wcarbo.utils;

import android.util.Log;

import com.example.chenguozhen.wcarbo.Bean.JSON.Collectionweibo;
import com.example.chenguozhen.wcarbo.Bean.JSON.Collectionweibo_Pic_urls;
import com.example.chenguozhen.wcarbo.Bean.Gson.UsersBean;
import com.example.chenguozhen.wcarbo.Bean.Gson.test;
import com.example.chenguozhen.wcarbo.data.CollectionData;
import com.example.chenguozhen.wcarbo.data.Collection_Pic_urls_Data;
import com.example.chenguozhen.wcarbo.data.FriendsData;
import com.google.gson.Gson;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 查询Hotweibo api（）
     * @param url
     * @param access_token
     */
    public static void queryHotweibo(String url, String access_token){
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        builder.addQueryParameter(Oauth2AccessToken.KEY_ACCESS_TOKEN,access_token);

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
                JSONUitily.paerseJSON_Hotweibo(responseData);
            }
        });
    }

    /**
     *
     * @param url
     * @param access_token
     */
    public static void QueryFravouiteweibo(final String url, final String access_token){
        final OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        builder.addQueryParameter(Oauth2AccessToken.KEY_ACCESS_TOKEN,access_token);
        Request request = new Request.Builder()
                .url(builder.build())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                List<Collectionweibo> collectionweibos = JSONUitily.collectionweiboList(responseData);
                final CollectionData collectionData = new CollectionData();
                collectionData.onLoad(collectionweibos);
                List<Collectionweibo_Pic_urls> collectionweiboPicUrls = JSONUitily.collectionweibo_pic_urlsList(responseData);
                final Collection_Pic_urls_Data collectionPicUrlsData = new Collection_Pic_urls_Data();
                collectionPicUrlsData.onLoad(collectionweiboPicUrls);
                final int count  = Pagecount(responseData);
                for (int i = 2; i <= count; i++) {
                    client.newCall(builder(i,url,access_token)).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData = response.body().string();
                            List<Collectionweibo> collectionweibos = JSONUitily.collectionweiboList(responseData);
                            collectionData.onLoad(collectionweibos);
                            List<Collectionweibo_Pic_urls> collectionweiboPicUrls = JSONUitily.collectionweibo_pic_urlsList(responseData);
                            collectionPicUrlsData.onLoad(collectionweiboPicUrls);
                        }
                    });
                }
            }
        });
    }

    public static void favouite(final String url, final String access_token){
        final List<String> favouite = new ArrayList<String>();
        final OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        builder.addQueryParameter(Oauth2AccessToken.KEY_ACCESS_TOKEN,access_token);
        Request request = new Request.Builder()
                .url(builder.build())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Log.d("test",responseData);
                List<Collectionweibo> collectionweibo = JSONUitily.collectionweiboList(responseData);
                Log.d("test",favouite.size()+"favouite_one");
                final int count  = Pagecount(responseData);
                Log.d("test",count+"favouite_count");
                for (int i = 2; i <= count; i++) {
                    client.newCall(builder(i,url,access_token)).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData = response.body().string();
                            Log.d("test",responseData);
                            favouite.add(responseData);
                            Log.d("test",favouite.size()+"favouite_more");
                        }
                    });
                }
            }
        });
        Log.d("test",favouite.size()+"favouite_last");
    }

    public static List<Collectionweibo> favouiteweibo(List<String> favouite){
        List<Collectionweibo> collectionweibos = new ArrayList<Collectionweibo>();
        for (int i = 0; i < favouite.size(); i++) {
            List<Collectionweibo> collectionweibo = JSONUitily.collectionweiboList(favouite.get(i));
            Log.d("test",collectionweibo.size()+"favouiteweibo.i");
            collectionweibos.addAll(collectionweibo);
        }
        Log.d("test",collectionweibos.size()+"favouiteweibo");
        return collectionweibos;
    }

    public static List<Collectionweibo_Pic_urls> pic_urls(List<String> favouite){
        List<Collectionweibo_Pic_urls> pic_urls = new ArrayList<Collectionweibo_Pic_urls>();
        for (int i = 0; i < favouite.size(); i++) {
            List<Collectionweibo_Pic_urls> collectionweibo = JSONUitily.collectionweibo_pic_urlsList(favouite.get(i));
            pic_urls.addAll(collectionweibo);
        }
        return pic_urls;
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

    public static Request usersBean(String url,String token,String screen_name){
        UsersBean usersBean = null;
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        builder.addQueryParameter(Oauth2AccessToken.KEY_ACCESS_TOKEN, token);
        builder.addQueryParameter("screen_name",screen_name);
        Request request = new Request.Builder().url(builder.build()).build();
        return request;
    }
}
