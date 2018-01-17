package com.example.chenguozhen.wcarbo.RecyclerView.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.chenguozhen.wcarbo.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chenguozhen on 2018/1/17.
 */

public class RetweetedDeletedViewHolder extends RecyclerView.ViewHolder{
    public CircleImageView retweeted_weibo_deleted_avatar;
    public TextView retweeted_weibo_deleted_name;
    public TextView retweeted_weibo_deleted_time;
    public TextView retweeted_weibo_deleted_source;
    public CheckBox retweeted_weibo_deleted_collection;
    public TextView retweeted_weibo_deleted_contnet_text;
    public Button retweeted_weibo_deleted_forward;
    public Button retweeted_weibo_deleted_comment;
    public Button retweeted_weibo_deleted_thumbsup;

    public TextView weibo_deleted_text_retweeted;


    public RetweetedDeletedViewHolder(View itemView) {
        super(itemView);
        retweeted_weibo_deleted_avatar = (CircleImageView)itemView.findViewById(R.id.retweeted_weibo_deleted_avatar);
        retweeted_weibo_deleted_name = (TextView)itemView.findViewById(R.id.retweeted_weibo_deleted_name);
        retweeted_weibo_deleted_time = (TextView)itemView.findViewById(R.id.retweeted_weibo_deleted_time);
        retweeted_weibo_deleted_source = (TextView)itemView.findViewById(R.id.retweeted_weibo_deleted_source);
        retweeted_weibo_deleted_collection = (CheckBox)itemView.findViewById(R.id.retweeted_weibo_deleted_boolean);
        retweeted_weibo_deleted_contnet_text = (TextView)itemView.findViewById(R.id.retweeted_weibo_deleted_text);
        retweeted_weibo_deleted_forward = (Button)itemView.findViewById(R.id.retweeted_weibo_deleted_forward);
        retweeted_weibo_deleted_comment = (Button)itemView.findViewById(R.id.retweeted_weibo_deleted_comment);
        retweeted_weibo_deleted_thumbsup = (Button)itemView.findViewById(R.id.retweeted_weibo_deleted_thumbsup);

        weibo_deleted_text_retweeted = (TextView)itemView.findViewById(R.id.weibo_deleted_text_retweeted);
    }
}
