package com.example.chenguozhen.wcarbo.utils;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.chenguozhen.wcarbo.data.FriendsData;
import com.example.chenguozhen.wcarbo.wcarbo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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
     *
     * @param url
     * @param access_token
     * @param cid
     * @return
     */
    public static Request destory_builder(String url, String access_token, String cid){
        RequestBody body = new FormBody.Builder()
                .add(Oauth2AccessToken.KEY_ACCESS_TOKEN, access_token)
                .add("cid", cid)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
       return request;
    }

    /**
     * 收藏
     * @param page
     * @param url
     * @param access_token
     * @return
     */
    public static Request favouite_builder(int page,String url,String access_token){
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        builder.addQueryParameter(Oauth2AccessToken.KEY_ACCESS_TOKEN,access_token);
        builder.addQueryParameter("page", String.valueOf(page));
        Request request = new Request.Builder()
                .url(builder.build())
                .build();
        return request;
    }

    /**
     * 获取当前登录用户所发出的评论列表
     * @param url
     * @param max_id
     * @param access_token
     * @return
     */
    public static Request byme_builder(String url,long max_id,String access_token){
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        builder.addQueryParameter(Oauth2AccessToken.KEY_ACCESS_TOKEN,access_token);
        builder.addQueryParameter("max_id", String.valueOf(max_id));
        Request request = new Request.Builder()
                .url(builder.build())
                .build();
        return request;
    }

    /**
     * 评论
     * @param url
     * @param next_cursor
     * @param access_token
     * @param idstr
     * @return
     */
    public static Request budiler(String url,long next_cursor,String access_token,String idstr){
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        builder.addQueryParameter(Oauth2AccessToken.KEY_ACCESS_TOKEN,access_token);
        builder.addQueryParameter("id",idstr);
        builder.addQueryParameter("max_id", String.valueOf(next_cursor));
        Request request = new Request.Builder()
                .url(builder.build())
                .build();
        return request;
    }

    /**
     * 微博时间轴
     * @param url
     * @param max_id
     * @param since_id
     * @param access_token
     * @return
     */
    public static Request timeline_builder(String url,long max_id,long since_id,String access_token){
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        builder.addQueryParameter(Oauth2AccessToken.KEY_ACCESS_TOKEN,access_token);
        builder.addQueryParameter("max_id", String.valueOf(max_id));
        builder.addQueryParameter("since_id",String.valueOf(since_id));
        Request request = new Request.Builder()
                .url(builder.build())
                .build();
        return request;
    }

    public static Request user_builder(String url,String access_token,String uid){
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        builder.addQueryParameter(Oauth2AccessToken.KEY_ACCESS_TOKEN,access_token);
        builder.addQueryParameter("uid", uid);
        Request request = new Request.Builder()
                .url(builder.build())
                .build();
        return request;
    }

    public static Request create_builder(String url,String access_token,String id,String comment){
        RequestBody body = new FormBody.Builder()
                .add(Oauth2AccessToken.KEY_ACCESS_TOKEN, access_token)
                .add("id",id)
                .add("comment",comment)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return request;
    }

    public static Request reply_builder(String url,String access_token,String id,String cid,String comment){
        RequestBody body = new FormBody.Builder()
                .add(Oauth2AccessToken.KEY_ACCESS_TOKEN, access_token)
                .add("id",id)
                .add("cid", cid)
                .add("comment",comment)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return request;
    }


}
