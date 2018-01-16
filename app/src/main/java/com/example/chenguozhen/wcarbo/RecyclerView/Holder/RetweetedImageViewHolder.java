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
 * Created by chenguozhen on 2018/1/6.
 */

public class RetweetedImageViewHolder extends RecyclerView.ViewHolder{
    public CircleImageView retweeted_weibo_iamge_avatar;
    public TextView retweeted_weibo_iamge_name;
    public TextView retweeted_weibo_iamge_time;
    public TextView retweeted_weibo_iamge_source;
    public CheckBox retweeted_weibo_iamge_collection;
    public TextView retweeted_weibo_iamge_contnet_text;
    public Button retweeted_weibo_iamge_forward;
    public Button retweeted_weibo_iamge_comment;
    public Button retweeted_weibo_iamge_thumbsup;

    public CircleImageView weibo_iamge_avatar_retweeted;
    public TextView weibo_iamge_name_retweeted;
    public TextView weibo_iamge_time_retweeted;
    public TextView weibo_iamge_source_retweeted;
    public TextView weibo_iamge_contnet_text_retweeted;
    public NineGridView weibo_image_ninegridimageview_retweeted;
    public Button weibo_iamge_forward_retweeted;
    public Button weibo_iamge_comment_retweeted;
    public Button weibo_iamge_thumbsup_retweeted;

    public RetweetedImageViewHolder(View itemView) {
        super(itemView);
        retweeted_weibo_iamge_avatar = (CircleImageView)itemView.findViewById(R.id.retweeted_weibo_iamge_avatar);
        retweeted_weibo_iamge_name = (TextView)itemView.findViewById(R.id.retweeted_weibo_iamge_name);
        retweeted_weibo_iamge_time = (TextView)itemView.findViewById(R.id.retweeted_weibo_iamge_time);
        retweeted_weibo_iamge_source = (TextView)itemView.findViewById(R.id.retweeted_weibo_iamge_source);
        retweeted_weibo_iamge_collection = (CheckBox)itemView.findViewById(R.id.retweeted_weibo_iamge_boolean);
        retweeted_weibo_iamge_contnet_text = (TextView)itemView.findViewById(R.id.retweeted_weibo_iamge_text);
        retweeted_weibo_iamge_forward = (Button)itemView.findViewById(R.id.retweeted_weibo_iamge_forward);
        retweeted_weibo_iamge_comment = (Button)itemView.findViewById(R.id.retweeted_weibo_iamge_comment);
        retweeted_weibo_iamge_thumbsup = (Button)itemView.findViewById(R.id.retweeted_weibo_iamge_thumbsup);

        weibo_iamge_avatar_retweeted = (CircleImageView)itemView.findViewById(R.id.weibo_iamge_avatar_retweeted);
        weibo_iamge_name_retweeted = (TextView)itemView.findViewById(R.id.weibo_iamge_name_retweeted);
        weibo_iamge_time_retweeted = (TextView)itemView.findViewById(R.id.weibo_iamge_time_retweeted);
        weibo_iamge_source_retweeted = (TextView)itemView.findViewById(R.id.weibo_iamge_source_retweeted);
        weibo_iamge_contnet_text_retweeted = (TextView)itemView.findViewById(R.id.weibo_iamge_text_retweeted);
        weibo_image_ninegridimageview_retweeted = (NineGridView)itemView.findViewById(R.id.weibo_image_ninegridimageview_retweeted);
        weibo_iamge_forward_retweeted = (Button)itemView.findViewById(R.id.weibo_iamge_forward_retweeted);
        weibo_iamge_comment_retweeted = (Button)itemView.findViewById(R.id.weibo_iamge_comment_retweeted);
        weibo_iamge_thumbsup_retweeted = (Button)itemView.findViewById(R.id.weibo_iamge_thumbsup_retweeted);
    }
}
