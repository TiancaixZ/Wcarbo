package com.example.chenguozhen.wcarbo.activity;

import android.content.DialogInterface;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.Adapter.viewpaperAdapter.ViewPaerFragmentPagerAdapter;

import com.example.chenguozhen.wcarbo.wcarbo;
import com.example.chenguozhen.wcarbo.widget.loginDialog;
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
    @BindView(R.id.viewpager) ViewPager viewpager;

    private ViewPaerFragmentPagerAdapter mAdapter;

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;

    private String token;
    private String uid ;
    private Long time;
    private boolean isLogin;

    /**
     * TAG:启动Fragment
     */
    public static final String EXTRA_CLICKBUTTONACTIVITY = "com.example.chenguozhen.wcarbo.activity.NAV_FRIENDS";

    private Oauth2AccessToken mAccessToken;
    private SsoHandler mSsoHandler;
    private wcarbo wcarbo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wcarbo = (wcarbo) getApplication();
        mSsoHandler = new SsoHandler(MainActivity.this);
        ButterKnife.bind(this);

        ReadToken();
        isLogin = isLogin();

        setToolbar();

        setNavView();

        setViewpager();
    }

    @OnClick({R.id.weibo_image_cardview, R.id.weibo_image_hotspot, R.id.weibo_image_perosn})
    public void ButtonClick(View v) {
        switch (v.getId()) {
            case R.id.weibo_image_cardview:
                viewpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.weibo_image_hotspot:
                if (isLogin){
                    viewpager.setCurrentItem(PAGE_TWO);
                }
                break;
            case R.id.weibo_image_perosn:
                if (isLogin) {
                    viewpager.setCurrentItem(PAGE_THREE);
                }
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
            finish();
        }

        @Override
        public void onFailure(WbConnectErrorMessage errorMessage) {
            Toast.makeText(MainActivity.this, errorMessage.getErrorMessage(), Toast.LENGTH_LONG).show();
            showButtonDialogFragment(getCurrentFocus());
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
        intent.putExtras(bundle);
        if (isLogin) {
            startActivity(intent);
        }
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.toolbar_nav_home);
        }
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

    private void setNavView() {
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
                    case R.id.nav_item_logout:
                        //登出
                        AccessTokenKeeper.clear(wcarbo.getApplicationContext());
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void setViewpager() {
        mAdapter = new ViewPaerFragmentPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(mAdapter);
        viewpager.setCurrentItem(PAGE_ONE);
    }

    public void showButtonDialogFragment(View view) {
        loginDialog loginDialog = new  loginDialog();
        loginDialog.show("登录到你的微博", "登陆过期或者暂时登陆", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mSsoHandler.authorizeWeb(new SelfWbAuthListener());
                Toast.makeText(MainActivity.this, "点击了确定 " + which, Toast.LENGTH_SHORT).show();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }, getSupportFragmentManager());
    }

    private void ReadToken(){
        Oauth2AccessToken tk = AccessTokenKeeper.readAccessToken(this);
        token = tk.getToken();
        uid = tk.getUid();
        time = tk.getExpiresTime();
    }

    private boolean isLogin(){
        boolean result = true;
        if (time == null){
            showButtonDialogFragment(getCurrentFocus());
            result = false;
        } else {
            long Systemtime=System.currentTimeMillis();
            if (time <= Systemtime){
                showButtonDialogFragment(getCurrentFocus());
                result = true;
            }
        }
        return result;
    }




}
