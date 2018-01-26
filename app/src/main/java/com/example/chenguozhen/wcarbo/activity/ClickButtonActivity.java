package com.example.chenguozhen.wcarbo.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.chenguozhen.wcarbo.Bean.Gson.UsersBean;
import com.example.chenguozhen.wcarbo.utils.FragmentFactory;
import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.Bean.JSON.Frindes;
import com.example.chenguozhen.wcarbo.utils.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.chenguozhen.wcarbo.activity.MainActivity.EXTRA_CLICKBUTTONACTIVITY;

public class ClickButtonActivity extends AppCompatActivity {

    @BindView(R.id.click_toolbar) Toolbar toolbar;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public static String title = null;

    public static Frindes frindes;
    public static UsersBean usersBean;
    public static String detail_idstr;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_button);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        int resId = bundle.getInt(EXTRA_CLICKBUTTONACTIVITY);
        frindes = (Frindes) bundle.getSerializable("frindes");
        usersBean = (UsersBean) bundle.getSerializable("UserBean");
        detail_idstr = bundle.getString("detail");

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });
        toolbar.setTitle(title);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.click_fragment, FragmentFactory.CreateByNav(resId));
        fragmentTransaction.commit();

    }
}
