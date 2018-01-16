package com.example.chenguozhen.wcarbo.RecyclerView.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.chenguozhen.wcarbo.R;
import com.lzy.ninegrid.NineGridView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chenguozhen on 2018/1/2.
 */

public class ImageViewHolder extends RecyclerView.ViewHolder{

    public CircleImageView weibo_image_avatar;
    public TextView weibo_image_name;
    public TextView weibo_image_time;
    public TextView weibo_image_source;
    public CheckBox weibo_image_checkbox;
    public TextView weibo_image_contnet_text;
    public NineGridView weibo_image_ninegridimageview;
    public Button weibo_image_forward;
    public Button weibo_image_comment;
    public Button weibo_image_thumbsup;

    public ImageViewHolder(View itemView) {
        super(itemView);
        weibo_image_avatar = (CircleImageView)itemView.findViewById(R.id.weibo_image_avatar);
        weibo_image_name = (TextView)itemView.findViewById(R.id.weibo_image_name);
        weibo_image_time = (TextView)itemView.findViewById(R.id.weibo_image_time);
        weibo_image_source = (TextView)itemView.findViewById(R.id.weibo_image_source);
        weibo_image_checkbox = (CheckBox)itemView.findViewById(R.id.weibo_image_boolean);
        weibo_image_contnet_text = (TextView)itemView.findViewById(R.id.weibo_image_text);
        weibo_image_ninegridimageview = (NineGridView)itemView.findViewById(R.id.weibo_image_ninegridimageview);
        weibo_image_forward = (Button)itemView.findViewById(R.id.weibo_image_forward);
        weibo_image_comment = (Button)itemView.findViewById(R.id.weibo_image_comment);
        weibo_image_thumbsup = (Button)itemView.findViewById(R.id.weibo_image_thumbsup);

    }

}
