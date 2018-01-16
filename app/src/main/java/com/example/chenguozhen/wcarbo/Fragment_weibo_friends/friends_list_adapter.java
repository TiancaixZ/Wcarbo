package com.example.chenguozhen.wcarbo.Fragment_weibo_friends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.activity.ClickButtonActivity;
import com.example.chenguozhen.wcarbo.Bean.JSON.Frindes;
import com.example.chenguozhen.wcarbo.wcarbo;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by chenguozhen on 2017/11/28.
 */

public class friends_list_adapter extends RecyclerView.Adapter<friends_list_adapter.ViewHolder>{

    private List<Frindes> mUsers;
    private Fragment mFragment;

    static class ViewHolder extends  RecyclerView.ViewHolder{

       View friendsview;
       TextView friends_list_name;
       CircleImageView firndes_imgaeview;

        public ViewHolder(View itemView) {
            super(itemView);
            friendsview = itemView;
            friends_list_name = (TextView) itemView.findViewById(R.id.friends_list_name);
            firndes_imgaeview = (CircleImageView) itemView.findViewById(R.id.firndes_imgaeview);

        }
    }

    public friends_list_adapter(List<Frindes>users, Fragment fragment){
        mUsers = users;
        mFragment = fragment;
    }

    @Override
    public friends_list_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.laoyout_recyclerview_frineds_list,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.friendsview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Frindes frindes = mUsers.get(position);
                Intent intent = new Intent(wcarbo.getContext(),ClickButtonActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("com.example.chenguozhen.wcarbo.activity.NAV_FRIENDS", R.id.friends_list_name);
                bundle.putSerializable("frindes",frindes);
                intent.putExtras(bundle);
                mFragment.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(friends_list_adapter.ViewHolder holder, int position) {
        String usersBean = mUsers.get(position).getScreen_name();
        holder.friends_list_name.setText(usersBean);
        String pirctureurl = mUsers.get(position).getAvatar_large();
        Glide.with(mFragment)
                .load(pirctureurl)
                .into(holder.firndes_imgaeview);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }



}
