package com.example.chenguozhen.wcarbo.RecyclerView.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.chenguozhen.wcarbo.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chenguozhen on 2018/1/19.
 */

public class WeibobaseReplyViewHodler extends RecyclerView.ViewHolder{

    public CircleImageView reply_avatar;
    public TextView reply_name;
    public TextView reply_created_at;
    public TextView reply_source;
    public TextView reply_content;
    public TextView reply_name_1;
    public TextView reply_content_1;
    public TextView reply_name_2;
    public TextView reply_content_2;
    public TextView base_reply_more;

    public WeibobaseReplyViewHodler(View itemView) {
        super(itemView);
        reply_avatar = (CircleImageView)itemView.findViewById(R.id.reply_avatar);
        reply_name = (TextView)itemView.findViewById(R.id.reply_name);
        reply_created_at = (TextView)itemView.findViewById(R.id.reply_created_at);
        reply_source = (TextView)itemView.findViewById(R.id.reply_source);
        reply_content = (TextView)itemView.findViewById(R.id.reply_content);
        reply_name_1 = (TextView)itemView.findViewById(R.id.reply_name_1);
        reply_content_1 = (TextView)itemView.findViewById(R.id.reply_content_1);
        reply_name_2 = (TextView)itemView.findViewById(R.id.reply_name_2);
        reply_content_2 = (TextView)itemView.findViewById(R.id.reply_content_2);
        base_reply_more = (TextView)itemView.findViewById(R.id.base_reply_more);
    }
}
