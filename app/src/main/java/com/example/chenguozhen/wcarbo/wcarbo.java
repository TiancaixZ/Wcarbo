package com.example.chenguozhen.wcarbo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import org.litepal.LitePal;

/**
 * Created by chenguozhen on 2017/12/4.
 */

public class wcarbo extends Application{

    private static Context context;
    private String token;
    private String uid ;
    private Long time;
    private boolean timeover;


    @Override
    public void onCreate() {
        super.onCreate();
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.d("TAG", "Max memory is " + maxMemory + "KB");
        LitePal.initialize(this);
        WbSdk.install(this,new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE));
        ReadToken();
        if (time >= System.currentTimeMillis()){
            timeover = true;
        } else {
            timeover = false;
        }
        LitePal.getDatabase();
        context = getApplicationContext();
        long time=System.currentTimeMillis();
        Log.d("TAG",time+"time");
    }

    /**
     * 获取oauth2accesstoken.
     */
    private void ReadToken(){
        Oauth2AccessToken tk = AccessTokenKeeper.readAccessToken(this);
        token = tk.getToken();
        uid = tk.getUid();
        time = tk.getExpiresTime();
    }


    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        wcarbo.context = context;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isTimeover() {
        return timeover;
    }

    public void setTimeover(boolean timeover) {
        this.timeover = timeover;
    }
}
