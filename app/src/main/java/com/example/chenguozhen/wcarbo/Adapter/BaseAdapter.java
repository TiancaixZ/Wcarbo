package com.example.chenguozhen.wcarbo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.chenguozhen.wcarbo.Bean.JSON.Comment;
import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.RecyclerView.Holder.WeibobaseReplyViewHodler;
import com.example.chenguozhen.wcarbo.RecyclerView.Holder.WeibobaseViewHolder;

import java.util.List;

/**
 * Created by chenguozhen on 2018/1/19.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int BASE = 1;
    private final static int BASEREPLY = 2;

    List<T> mDataList;
    Fragment mFragment;

    public BaseAdapter(List<T> DataList,Fragment fragment) {
        this.mDataList = DataList;
        this.mFragment = fragment;
    }

    @Override
    public int getItemViewType(int position) {
        Comment comment = PositionComment(position);
        if (comment.isReply()){
            return BASEREPLY;
        } else {
            return BASE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == BASE) {
            view = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.layout_fragment_weibo_base, parent, false);
            final WeibobaseViewHolder holder = new WeibobaseViewHolder(view);
            return holder;
        } else if(viewType == BASEREPLY){
            view = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.layout_fragment_base_reply, parent, false);
            final WeibobaseReplyViewHodler hodler = new WeibobaseReplyViewHodler(view);
            return hodler;
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Comment comment = PositionComment(position);
        String avatar = comment.getUsersBean().getAvatar_hd();
        String name = comment.getUsersBean().getScreen_name();
        String created_at = comment.getCreated_at();
        String source = comment.getSource();
        String content = comment.getText();

        if (holder instanceof WeibobaseViewHolder){
            Glide.with(mFragment)
                    .load(avatar)
                    .into(((WeibobaseViewHolder) holder).avatar);
            ((WeibobaseViewHolder) holder).name.setText(name);
            ((WeibobaseViewHolder) holder).created_at.setText(created_at);
            ((WeibobaseViewHolder) holder).source.setText(source);
            ((WeibobaseViewHolder) holder).content.setText(content);
        } else if (holder instanceof WeibobaseReplyViewHodler){
            Glide.with(mFragment)
                    .load(avatar)
                    .into(((WeibobaseReplyViewHodler) holder).reply_avatar);
            ((WeibobaseReplyViewHodler) holder).reply_name.setText(name);
            ((WeibobaseReplyViewHodler) holder).reply_created_at.setText(created_at);
            ((WeibobaseReplyViewHodler) holder).reply_source.setText(source);
            ((WeibobaseReplyViewHodler) holder).reply_content.setText(content);

            ((WeibobaseReplyViewHodler) holder).reply_name_1.setText();
            ((WeibobaseReplyViewHodler) holder).reply_content_1.setText();
            ((WeibobaseReplyViewHodler) holder).reply_name_2.setText();
            ((WeibobaseReplyViewHodler) holder).reply_content_2.setText();
            ((WeibobaseReplyViewHodler) holder).base_reply_more.setText();
        }

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    protected abstract Comment PositionComment(int position);

    protected Comment Reply
}
