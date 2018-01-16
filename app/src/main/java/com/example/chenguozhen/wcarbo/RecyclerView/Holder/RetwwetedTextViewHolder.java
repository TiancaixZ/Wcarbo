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

public class RetwwetedTextViewHolder extends RecyclerView.ViewHolder{

    public CircleImageView retweeted_weibo_text_avatar;
    public TextView retweeted_weibo_text_name;
    public TextView retweeted_weibo_text_time;
    public TextView retweeted_weibo_text_source;
    public CheckBox retweeted_weibo_text_collection;
    public TextView retweeted_weibo_text_contnet_text;
    public Button retweeted_weibo_text_forward;
    public Button retweeted_weibo_text_comment;
    public Button retweeted_weibo_text_thumbsup;

    public CircleImageView weibo_text_avatar_retweeted;
    public TextView weibo_text_name_retweeted;
    public TextView weibo_text_time_retweeted;
    public TextView weibo_text_source_retweeted;
    public TextView weibo_text_contnet_text_retweeted;
    public Button weibo_text_forward_retweeted;
    public Button weibo_text_comment_retweeted;
    public Button weibo_text_thumbsup_retweeted;

    public RetwwetedTextViewHolder(View itemView) {
        super(itemView);
        retweeted_weibo_text_avatar = (CircleImageView)itemView.findViewById(R.id.retweeted_weibo_text_avatar);
        retweeted_weibo_text_name = (TextView)itemView.findViewById(R.id.retweeted_weibo_text_name);
        retweeted_weibo_text_time = (TextView)itemView.findViewById(R.id.retweeted_weibo_text_time);
        retweeted_weibo_text_source = (TextView)itemView.findViewById(R.id.retweeted_weibo_text_source);
        retweeted_weibo_text_collection = (CheckBox)itemView.findViewById(R.id.retweeted_weibo_text_boolean);
        retweeted_weibo_text_contnet_text = (TextView)itemView.findViewById(R.id.retweeted_weibo_text_text);
        retweeted_weibo_text_forward = (Button)itemView.findViewById(R.id.retweeted_weibo_text_forward);
        retweeted_weibo_text_comment = (Button)itemView.findViewById(R.id.retweeted_weibo_text_comment);
        retweeted_weibo_text_thumbsup = (Button)itemView.findViewById(R.id.retweeted_weibo_text_thumbsup);

        weibo_text_avatar_retweeted = (CircleImageView)itemView.findViewById(R.id.weibo_text_avatar_retweeted);
        weibo_text_name_retweeted = (TextView)itemView.findViewById(R.id.weibo_text_name_retweeted);
        weibo_text_time_retweeted = (TextView)itemView.findViewById(R.id.weibo_text_time_retweeted);
        weibo_text_source_retweeted = (TextView)itemView.findViewById(R.id.weibo_text_source_retweeted);
        weibo_text_contnet_text_retweeted = (TextView)itemView.findViewById(R.id.weibo_text_text_retweeted);
        weibo_text_forward_retweeted = (Button)itemView.findViewById(R.id.weibo_text_forward_retweeted);
        weibo_text_comment_retweeted = (Button)itemView.findViewById(R.id.weibo_text_comment_retweeted);
        weibo_text_thumbsup_retweeted = (Button)itemView.findViewById(R.id.weibo_text_thumbsup_retweeted);
    }
}
