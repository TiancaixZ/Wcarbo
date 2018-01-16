package com.example.chenguozhen.wcarbo.API;

import com.example.chenguozhen.wcarbo.Bean.JSON.Frindes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chenguozhen on 2018/1/11.
 */

public interface Friends {
    @GET("https://api.weibo.com/2/friendships/friends.json?source=3867086258")
    Call<Frindes> frindes(@Query("source")String source,@Query("access_token")String token,@Query("uid")String uid);
}
