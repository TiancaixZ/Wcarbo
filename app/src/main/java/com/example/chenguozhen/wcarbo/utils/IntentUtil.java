package com.example.chenguozhen.wcarbo.utils;

import android.content.Intent;
import android.os.Bundle;

import com.example.chenguozhen.wcarbo.Bean.Gson.UsersBean;
import com.example.chenguozhen.wcarbo.activity.ClickButtonActivity;
import com.example.chenguozhen.wcarbo.wcarbo;

import static com.example.chenguozhen.wcarbo.activity.MainActivity.EXTRA_CLICKBUTTONACTIVITY;

/**
 * Created by chenguozhen on 2018/1/30.
 */

public class IntentUtil {

    public static void intent(int resId) {
        Intent intent = new Intent(wcarbo.getContext(), ClickButtonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_CLICKBUTTONACTIVITY, resId);
        intent.putExtras(bundle);
        wcarbo.getContext().startActivity(intent);
    }

    public static void idstr_intent(int resId,String idstr){
        Intent intent = new Intent(wcarbo.getContext(), ClickButtonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_CLICKBUTTONACTIVITY, resId);
        bundle.putString("detail", idstr);
        intent.putExtras(bundle);
        wcarbo.getContext().startActivity(intent);
    }

    public static void usersBean_intent(int resId, UsersBean usersBean){
        Intent intent = new Intent(wcarbo.getContext(), ClickButtonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_CLICKBUTTONACTIVITY, resId);
        bundle.putSerializable("UserBean", usersBean);
        intent.putExtras(bundle);
        wcarbo.getContext().startActivity(intent);
    }

    public static void reply_intent(int resId,String idstr,String cid){
        Intent intent = new Intent(wcarbo.getContext(), ClickButtonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_CLICKBUTTONACTIVITY, resId);
        bundle.putString("detail",idstr);
        bundle.putString("cid",cid);
        intent.putExtras(bundle);
        wcarbo.getContext().startActivity(intent);
    }
}
