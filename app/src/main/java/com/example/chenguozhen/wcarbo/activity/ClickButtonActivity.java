package com.example.chenguozhen.wcarbo.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.chenguozhen.wcarbo.Bean.Gson.UsersBean;
import com.example.chenguozhen.wcarbo.utils.FragmentFactory;
import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.Bean.JSON.Frindes;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.chenguozhen.wcarbo.activity.MainActivity.EXTRA_CLICKBUTTONACTIVITY;

public class ClickButtonActivity extends AppCompatActivity {

    @BindView(R.id.click_fragment_toolbar) Toolbar click_fragmenttoolbar;

    public FragmentManager fragmentManager;
    public FragmentTransaction fragmentTransaction;

    public static Frindes frindes;
    public static UsersBean usersBean;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_button);

        ButterKnife.bind(this);

        click_fragmenttoolbar.setNavigationIcon(R.drawable.back);

        Bundle bundle = getIntent().getExtras();
        int resId = bundle.getInt(EXTRA_CLICKBUTTONACTIVITY);
        frindes = (Frindes) bundle.getSerializable("frindes");
        usersBean = (UsersBean) bundle.getSerializable("UserBean");

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.click_fragment, FragmentFactory.CreateByNav(resId));
        fragmentTransaction.commit();

    }
}
