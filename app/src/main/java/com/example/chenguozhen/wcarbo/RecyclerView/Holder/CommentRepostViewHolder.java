package com.example.chenguozhen.wcarbo.RecyclerView.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.chenguozhen.wcarbo.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chenguozhen on 2018/1/23.
 */

public class CommentRepostViewHolder extends RecyclerView.ViewHolder{

    public CircleImageView avatar;
    public TextView name;
    public TextView created_at;
    public TextView source;
    public TextView content;

    public CommentRepostViewHolder(View itemView) {
        super(itemView);
        avatar = (CircleImageView)itemView.findViewById(R.id.avatar);
        name = (TextView)itemView.findViewById(R.id.name);
        created_at = (TextView)itemView.findViewById(R.id.created_at);
        source = (TextView)itemView.findViewById(R.id.source);
        content = (TextView)itemView.findViewById(R.id.content);
    }
}
