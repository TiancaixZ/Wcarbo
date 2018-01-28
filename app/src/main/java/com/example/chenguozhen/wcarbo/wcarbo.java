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

public class wcarbo extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        LitePal.initialize(this);
        WbSdk.install(this, new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE));
        LitePal.getDatabase();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        wcarbo.context = context;
    }

}

