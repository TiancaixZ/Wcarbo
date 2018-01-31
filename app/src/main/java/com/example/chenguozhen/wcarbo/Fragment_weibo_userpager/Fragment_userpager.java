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

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chenguozhen on 2017/12/12.
 */

public class Fragment_userpager extends Fragment{

    private String url = "https://api.weibo.com/2/users/show.json?source=3867086258";
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

    public static final int UPDATE_USERBEAN = 4;

    private boolean stopThread = false;

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_USERBEAN:

                    setUpdateUserbeanpager(usersBean);
                    break;
                default:
                    break;
            }
        }
    };

    public static Fragment_userpager newInstance(UsersBean usersBean){
        Fragment_userpager fragment_userpager = new Fragment_userpager();
        Bundle bundle = new Bundle();
        bundle.putSerializable("usersBean",usersBean);
        fragment_userpager.setArguments(bundle);
        return fragment_userpager;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            while (!stopThread){
                if (getArguments()!=null){
                    usersBean = (UsersBean) getArguments().getSerializable("userbean");
                    doBackWeiboUserweibopager();
                }
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        stopThread = true;
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
     * 后台处理数据发送message(UsersBean)
     */
    private void doBackWeiboUserweibopager(){
        Message message = Message.obtain();
        message.what = UPDATE_USERBEAN;
        handler.sendMessage(message);
    }

}
