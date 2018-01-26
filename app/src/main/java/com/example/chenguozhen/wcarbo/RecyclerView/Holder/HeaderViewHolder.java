package com.example.chenguozhen.wcarbo.RecyclerView.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.chenguozhen.wcarbo.R;

/**
 * Created by chenguozhen on 2018/1/24.
 */

public class HeaderViewHolder extends RecyclerView.ViewHolder{

    public TextView header_textView;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        header_textView = (TextView)itemView.findViewById(R.id.header_textview);
    }
}
