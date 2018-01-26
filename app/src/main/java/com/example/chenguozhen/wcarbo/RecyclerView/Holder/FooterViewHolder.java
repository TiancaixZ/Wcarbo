package com.example.chenguozhen.wcarbo.RecyclerView.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.chenguozhen.wcarbo.R;

/**
 * Created by chenguozhen on 2018/1/23.
 */

public class FooterViewHolder extends RecyclerView.ViewHolder{
    public TextView footer_textView;

    public FooterViewHolder(View itemView) {
        super(itemView);
        footer_textView = (TextView)itemView.findViewById(R.id.footer_textview);
    }
}
