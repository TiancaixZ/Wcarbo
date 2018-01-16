package com.example.chenguozhen.wcarbo.RecyclerView.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.chenguozhen.wcarbo.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chenguozhen on 2018/1/2.
 */

public class TextViewHolder extends RecyclerView.ViewHolder{

    public CircleImageView weibo_text_avatar;
    public TextView weibo_text_name;
    public TextView weibo_text_time;
    public TextView weibo_text_source;
    public CheckBox weibo_text_collection;
    public TextView weibo_text_contnet_text;
    public Button weibo_text_forward;
    public Button weibo_text_comment;
    public Button weibo_text_thumbsup;

    public TextViewHolder(View itemView) {
        super(itemView);
        weibo_text_avatar = (CircleImageView)itemView.findViewById(R.id.weibo_text_avatar);
        weibo_text_name = (TextView)itemView.findViewById(R.id.weibo_text_name);
        weibo_text_time = (TextView)itemView.findViewById(R.id.weibo_text_time);
        weibo_text_source = (TextView)itemView.findViewById(R.id.weibo_text_source);
        weibo_text_collection = (CheckBox)itemView.findViewById(R.id.weibo_text_boolean);
        weibo_text_contnet_text = (TextView)itemView.findViewById(R.id.weibo_text_text);
        weibo_text_forward = (Button)itemView.findViewById(R.id.weibo_text_forward);
        weibo_text_comment = (Button)itemView.findViewById(R.id.weibo_text_comment);
        weibo_text_thumbsup = (Button)itemView.findViewById(R.id.weibo_text_thumbsup);
    }
}
