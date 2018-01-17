package com.example.chenguozhen.wcarbo.RecyclerView.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.chenguozhen.wcarbo.R;
/**
 * Created by chenguozhen on 2018/1/17.
 */

public class DeletedViewHolder  extends RecyclerView.ViewHolder{
    public TextView deleted_text;

    public DeletedViewHolder(View itemView) {
        super(itemView);
        deleted_text = (TextView)itemView.findViewById(R.id.weibo_deleted_text);

    }
}
