package com.example.chenguozhen.wcarbo.API;

import com.example.chenguozhen.wcarbo.Bean.Gson.emotions;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chenguozhen on 2018/1/10.
 */

public interface emotion {
    @GET("https://api.weibo.com/2/emotions.json")
    Call<emotions>emotions(@Query("source")String source,@Query("access_token")String token,@Query("type")String type);
}
