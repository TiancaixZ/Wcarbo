package com.example.chenguozhen.wcarbo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.chenguozhen.wcarbo.Bean.JSON.Comment;
import com.example.chenguozhen.wcarbo.Bean.JSON.Status;
import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.RecyclerView.Holder.CommentRepostViewHolder;
import com.example.chenguozhen.wcarbo.RecyclerView.Holder.FooterViewHolder;
import com.example.chenguozhen.wcarbo.RecyclerView.Holder.HeaderViewHolder;
import com.example.chenguozhen.wcarbo.utils.StringUtil;

import java.util.List;

/**
 * Created by chenguozhen on 2018/1/23.
 */

public abstract class CommentReportAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int HEADER = 1;
    private final int NORMAL = 2;
    private final int FOOTER = 3;

    private int mHeaderCount = 1;
    private int mBottomCount = 1;

    private List<T> mDataList;
    private Fragment mFragment;

    public CommentReportAdapter(List<T> DataList,Fragment Fragment) {
        this.mDataList = DataList;
        this.mFragment = Fragment;
    }

    public int getContentItemCount(){
        return mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getContentItemCount();
        if (mHeaderCount != 0 && position < mHeaderCount){
            return HEADER;
        } else if (mBottomCount != 0 && position >= (mHeaderCount + dataItemCount)){
            return FOOTER;
        } else {
            return NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == FOOTER){
            view = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.layout_fragment_weibo_footer,parent,false);
            final FooterViewHolder holder = new FooterViewHolder(view);
            return holder;
        } else if (viewType == NORMAL){
            view = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.layout_fragment_weibo_commentrepost, parent, false);
            final CommentRepostViewHolder holder = new CommentRepostViewHolder(view);
            return holder;
        } else if (viewType == HEADER){
            view = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.layout_fragment_weibo_header,parent,false);
            final HeaderViewHolder holder = new HeaderViewHolder(view);
            return holder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof CommentRepostViewHolder){
                String avatar = null;
                String name = null;
                String created_at = null;
                String source = null;
                String content = null;
                Comment comment = comment(position - mHeaderCount);
                Status status = status(position-mHeaderCount);
                if (status != null){
                    avatar = status.getUsersBean().getAvatar_hd();
                    name = status.getUsersBean().getScreen_name();
                    created_at = status.getCreated_at();
                    source =  StringUtil.getWeiboSource(status.getSource());
                    content = status.getText();
                } else if (comment != null){
                    avatar = comment.getUsersBean().getAvatar_hd();
                    name = comment.getUsersBean().getScreen_name();
                    created_at = comment.getCreated_at();
                    source =  StringUtil.getWeiboSource(comment.getSource());
                    content = comment.getText();
                }

                Glide.with(mFragment)
                        .load(avatar)
                        .into(((CommentRepostViewHolder) holder).avatar);
                ((CommentRepostViewHolder) holder).name.setText(name);
                ((CommentRepostViewHolder) holder).created_at.setText(created_at);
                ((CommentRepostViewHolder) holder).source.setText(source);
                ((CommentRepostViewHolder) holder).content.setText(content);
            } else if (holder instanceof FooterViewHolder){
                ((FooterViewHolder) holder).footer_textView.setText("已经咩有了");
            } else if (holder instanceof HeaderViewHolder){
                ((HeaderViewHolder) holder).header_textView.setText("时间倒序排序");
            }
    }

    @Override
    public int getItemCount() {
        return mDataList.size() + mHeaderCount + mBottomCount;
    }

    protected Comment comment(int position){
      return null;
    }

    protected Status status(int position){
        return null;
    }
}
