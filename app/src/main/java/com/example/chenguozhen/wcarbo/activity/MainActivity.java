package com.example.chenguozhen.wcarbo.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.viewpaperAdapter.ViewPaerFragmentPagerAdapter;

import com.example.chenguozhen.wcarbo.wcarbo;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view) NavigationView mNavView;
    @BindView(R.id.weibo_image_cardview) ImageButton weibo_image_cardview;
    @BindView(R.id.weibo_image_hotspot) ImageButton weibo_image_hopost;
    @BindView(R.id.weibo_image_perosn) ImageButton weibo_image_perosn;

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private ViewPaerFragmentPagerAdapter mAdapter;

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;

    /**
     * TAG:启动Fragment
     */
    public static final String EXTRA_CLICKBUTTONACTIVITY = "com.example.chenguozhen.wcarbo.activity.NAV_FRIENDS";

    /**
     * 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能
     */
    private Oauth2AccessToken mAccessToken;

    /**
     * 注意：SsoHandler 仅当 SDK 支持 SSO 时有效
     */
    private SsoHandler mSsoHandler;

    /**
     * application
     **/
    private wcarbo wcarbo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wcarbo = (wcarbo) getApplication();

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.toolbar_nav_home);
        }
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_item_profile:
                        //个人主页
                        init(R.id.nav_item_profile);
                        break;
                    case R.id.nav_item_friends:
                        //我的好友()
                        init(R.id.nav_item_friends);
                        break;
                    case R.id.nav_item_Collection:
                        //我的收藏
                        init(R.id.nav_item_Collection);
                        break;
                    case R.id.nav_item_DraftBox:
                        //草稿箱
                        break;
                    case R.id.nav_item_login:
                        //登陆
                            mSsoHandler.authorizeWeb(new SelfWbAuthListener());
                        break;
                    case R.id.nav_item_logout:
                        //登出
                        AccessTokenKeeper.clear(wcarbo.getApplicationContext());
                    default:
                        break;
                }
                return false;
            }
        });

        mAdapter = new ViewPaerFragmentPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(mAdapter);
        viewpager.setCurrentItem(PAGE_ONE);


        mSsoHandler = new SsoHandler(MainActivity.this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //打开项目
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }

    @OnClick({R.id.weibo_image_cardview, R.id.weibo_image_hotspot, R.id.weibo_image_perosn})
    public void ButtonClick(View v) {
        switch (v.getId()) {
            case R.id.weibo_image_cardview:
                viewpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.weibo_image_hotspot:
                viewpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.weibo_image_perosn:
                viewpager.setCurrentItem(PAGE_THREE);
                break;
            default:
                break;
        }
    }

    /**
     * 授权接口
     */
    private class SelfWbAuthListener implements com.sina.weibo.sdk.auth.WbAuthListener {
        @Override
        public void onSuccess(final Oauth2AccessToken token) {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAccessToken = token;
                    if (mAccessToken.isSessionValid()) {
                        // 保存 Token 到 SharedPreferences
                        AccessTokenKeeper.writeAccessToken(MainActivity.this, mAccessToken);
                        Toast.makeText(MainActivity.this,
                                "授权成功", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        @Override
        public void cancel() {
            Toast.makeText(MainActivity.this,
                    "取消授权", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onFailure(WbConnectErrorMessage errorMessage) {
            Toast.makeText(MainActivity.this, errorMessage.getErrorMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    /**
     * 启动clickbuttonactivty
     * @param resId
     */
    private void init(int resId) {
        Intent intent = new Intent(MainActivity.this, ClickButtonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_CLICKBUTTONACTIVITY, resId);
        Log.d("friends",resId+"main");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
