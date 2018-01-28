package com.example.chenguozhen.wcarbo.Fragment_weibo_userpager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chenguozhen.wcarbo.Bean.Gson.UsersBean;
import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.Bean.JSON.Frindes;
import com.example.chenguozhen.wcarbo.Bean.JSON.Users;
import com.example.chenguozhen.wcarbo.utils.Utility;
import com.example.chenguozhen.wcarbo.wcarbo;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import org.litepal.crud.DataSupport;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chenguozhen on 2017/12/12.
 */

public class Fragment_userpager extends Fragment{

    private String token;
    private String uId;
    private String url = "https://api.weibo.com/2/users/show.json?source=3867086258";
    private Frindes frindes;
    private UsersBean usersBean;

    @BindView(R.id.usepager_circleimage) CircleImageView usepager_circleimage;
    @BindView(R.id.usepager_name) TextView usepager_name;
    @BindView(R.id.usepager_gender) ImageView usepager_gender;
    @BindView(R.id.usepager_personalprofile) TextView usepager_profile;
    @BindView(R.id.usepager_location_textview) TextView usepager_location;
    @BindView(R.id.usepager_contion_textview) TextView usepager_contion;
    @BindView(R.id.usepager_attention_textview) TextView usepager_attention;
    @BindView(R.id.usepager_follower_textview) TextView usepager_follower;
    @BindView(R.id.usepager_recyclerview) RecyclerView usepager_recyclerview;

    public static final int UPDATE_USEPAGER = 1;
    public static final int UPDATE_FRINDESPAGER = 2;
    public static final int UPDATE_USERBEAN = 4;

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_USEPAGER:
                    Users user = (Users) msg.obj;
                    setUpdateUsepager(user);
                    break;
                case UPDATE_FRINDESPAGER:
                    setUpdateFrindespager(frindes);
                    break;
                case UPDATE_USERBEAN:
                    setUpdateUserbeanpager(usersBean);
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Oauth2AccessToken tk = AccessTokenKeeper.readAccessToken(getContext());
        token = tk.getToken();
        uId = tk.getUid();

        new Thread(new UeseQuery()).start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_userpager,container,false);
        ButterKnife.bind(this, view);

        return view;
    }

    private class UeseQuery implements Runnable{
        @Override
        public void run() {
            if (getArguments() == null){
                doBackUespager();
            } else if (getArguments().getSerializable("fridnes") != null){
                frindes = (Frindes) getArguments().getSerializable("fridnes");
                doBackFrindespager();
            } else if (getArguments().getSerializable("userbean") !=null){
                usersBean = (UsersBean) getArguments().getSerializable("userbean");
                doBackWeiboUserweibopager();
            }
        }
    }

    /**
     * 更新view(Uses)
     * @param user
     */
    private void setUpdateUsepager(Users user){
        Glide.with(Fragment_userpager.this)
                .load(user.getAvatar_hd())
                .into(usepager_circleimage);

        usepager_name.setText(user.getScreen_name());

        if (user.getGender().equals("f")){
            usepager_gender.setImageResource(R.drawable.women);
        }

        usepager_profile.setText(user.getDescription());

        usepager_location.setText(user.getLocation());

        usepager_contion.setText(user.getProfile_url());

        usepager_attention.setText(user.getFrindes_count()+ "");

        usepager_follower.setText(user.getFoolowers_count()+ "");
    }

    /**
     * 更新View(Frindes)
     * @param frindes
     */
    private void setUpdateFrindespager(Frindes frindes){
        Glide.with(Fragment_userpager.this)
                .load(frindes.getAvatar_large())
                .into(usepager_circleimage);

        usepager_name.setText(frindes.getScreen_name());

        if (frindes.getGender().equals("f")){
            usepager_gender.setImageResource(R.drawable.women);
        }

        usepager_profile.setText(frindes.getDescription());

        usepager_location.setText(frindes.getLocation());

        usepager_contion.setText(frindes.getProfile_image_url());

        usepager_attention.setText(frindes.getFrindes_count()+ "");

        usepager_follower.setText(frindes.getFollowers_count()+ "");
    }

    /**
     * 更新View(UsersBean)
     * @param usersBean
     */
    private void setUpdateUserbeanpager(UsersBean usersBean){
        Glide.with(Fragment_userpager.this)
                .load(usersBean.getAvatar_hd())
                .into(usepager_circleimage);

        usepager_name.setText(usersBean.getScreen_name());

        if (usersBean.getGender().equals("f")){
            usepager_gender.setImageResource(R.drawable.women);
        }

        usepager_profile.setText(usersBean.getDescription());

        usepager_location.setText(usersBean.getLocation());

        usepager_contion.setText(usersBean.getProfile_url());

        usepager_attention.setText(usersBean.getFriends_count()+ "");

        usepager_follower.setText(usersBean.getFollowers_count()+ "");
    }

    /**
     * 后台处理数据发送message(Use)
     */
    private void doBackUespager(){
        Utility.queryfriends(url,token,uId, Utility.QueryUse);
        Users user = DataSupport.findLast(Users.class);
        Message message = Message.obtain();
        message.obj = user;
        message.what = UPDATE_USEPAGER;
        handler.sendMessage(message);
    }

    /**
     * 后台处理数据发送message(Frindes)
     */
    private void doBackFrindespager(){
        Message message = Message.obtain();
        message.what = UPDATE_FRINDESPAGER;
        handler.sendMessage(message);
    }

    /**
     * 后台处理数据发送message(UsersBean)
     */
    private void doBackWeiboUserweibopager(){
        Message message = Message.obtain();
        message.what = UPDATE_USERBEAN;
        handler.sendMessage(message);
    }

}
