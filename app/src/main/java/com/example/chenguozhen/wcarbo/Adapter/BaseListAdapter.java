package com.example.chenguozhen.wcarbo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.chenguozhen.wcarbo.Bean.Gson.UsersBean;
import com.example.chenguozhen.wcarbo.Bean.JSON.Status;
import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.RecyclerView.Holder.DeletedViewHolder;
import com.example.chenguozhen.wcarbo.RecyclerView.Holder.ImageViewHolder;
import com.example.chenguozhen.wcarbo.RecyclerView.Holder.RetweetedDeletedViewHolder;
import com.example.chenguozhen.wcarbo.RecyclerView.Holder.RetweetedImageViewHolder;
import com.example.chenguozhen.wcarbo.RecyclerView.Holder.RetwwetedTextViewHolder;
import com.example.chenguozhen.wcarbo.RecyclerView.Holder.TextViewHolder;

import com.example.chenguozhen.wcarbo.utils.ImageLoader;
import com.example.chenguozhen.wcarbo.utils.StringUtil;
import com.example.chenguozhen.wcarbo.wcarbo;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.List;

/**
 * Created by chenguozhen on 2018/1/18.
 */

public abstract class BaseListAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int IMAGEVIEWTYPE = 1;
    private final int TEXTVIEWTYPE = 2;
    private final int RETWEETEDIMAGEVIEWYPTE = 3;
    private final int RETWEETEDTEXTVIEWYTPE = 4;
    private final int DELETEDTYPE = 5;
    private final int RETWEETEDDELETEDTYPE = 6;

    List<T> mDataList;
    Fragment mFragment;

    public BaseListAdapter(List<T> DataList,Fragment fragment) {
        this.mDataList = DataList;
        this.mFragment = fragment;
    }

    @Override
    public int getItemViewType(int position) {
        Status status = PositionStatus(position);
        if (status.getRetweeted_status() == null){
            if (status.isDeleted()){
                return DELETEDTYPE;
            } else {
                if (status.isPhoto()){
                    return IMAGEVIEWTYPE;
                } else {
                    return TEXTVIEWTYPE;
                }
            }
        } else {
            if (status.getRetweeted_status().isDeleted()){
                return RETWEETEDDELETEDTYPE;
            } else {
                if (status.getRetweeted_status().isPhoto()){
                    return RETWEETEDIMAGEVIEWYPTE;
                } else {
                    return RETWEETEDTEXTVIEWYTPE;
                }
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == IMAGEVIEWTYPE){
            view = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.layout_fragment_weibo_image,parent,false);
            final ImageViewHolder holder = new ImageViewHolder(view);
            holder.weibo_image_avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    userpage(position);

                }
            });
            holder.weibo_image_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    userpage(position);
                }
            });
            return holder;
        } else if (viewType == TEXTVIEWTYPE){
            view = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.layout_fragment_weibo_text,parent,false);
            final TextViewHolder holder = new TextViewHolder(view);
            holder.weibo_text_avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    userpage(position);
                }
            });
            holder.weibo_text_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    userpage(position);
                }
            });
            return holder;
        } else if (viewType == RETWEETEDIMAGEVIEWYPTE){
            view = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.layout_fragment_weibo_retweeted_status_image,parent,false);
            final RetweetedImageViewHolder holder = new RetweetedImageViewHolder(view);
            holder.retweeted_weibo_iamge_avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    userpage(position);
                }
            });
            holder.retweeted_weibo_iamge_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    userpage(position);
                }
            });
            holder.weibo_iamge_avatar_retweeted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    re_userpage(position);
                }
            });
            holder.weibo_iamge_name_retweeted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    re_userpage(position);
                }
            });
            return holder;
        } else if (viewType == RETWEETEDTEXTVIEWYTPE){
            view = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.layout_fragment_weibo_retweeted_status_text,parent,false);
            final RetwwetedTextViewHolder holder = new RetwwetedTextViewHolder(view);
            holder.retweeted_weibo_text_avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    userpage(position);
                }
            });
            holder.retweeted_weibo_text_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    userpage(position);
                }
            });
            holder.weibo_text_avatar_retweeted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                   re_userpage(position);
                }
            });
            holder.weibo_text_name_retweeted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    re_userpage(position);
                }
            });
            return holder;
        } else if (viewType == DELETEDTYPE){
            view = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.layout_fragment_weibo_deleted,parent,false);
            final DeletedViewHolder holder = new DeletedViewHolder(view);
            return holder;
        } else if (viewType == RETWEETEDDELETEDTYPE){
            view = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.layout_fragment_weibo_retweeted_deleted,parent,false);
            final RetweetedDeletedViewHolder holder = new RetweetedDeletedViewHolder(view);
            holder.retweeted_weibo_deleted_avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    userpage(position);
                }
            });
            holder.retweeted_weibo_deleted_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    userpage(position);
                }
            });
            return holder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Status status = PositionStatus(position);

        String time = status.getCreated_at();
        String source = StringUtil.getWeiboSource(status.getSource());
        String content = status.getText();
        boolean favorited = status.isFavorited();
        boolean truncated = status.isTruncated();
        int reposts_count = status.getReposts_count();
        int comments_count = status.getComments_count();
        int attitudes_count = status.getAttitudes_count();

        if (holder instanceof ImageViewHolder){
            UsersBean usersBean = status.getUsersBean();
            String name = usersBean.getScreen_name();
            String avatar = usersBean.getAvatar_hd();
            Glide.with(mFragment)
                    .load(avatar)
                    .into(((ImageViewHolder) holder).weibo_image_avatar);
            ((ImageViewHolder) holder).weibo_image_name.setText(name);
            ((ImageViewHolder) holder).weibo_image_time.setText(time);
            ((ImageViewHolder) holder).weibo_image_source.setText(source);
            ((ImageViewHolder) holder).weibo_image_contnet_text.setText(content);
            ((ImageViewHolder) holder).weibo_image_forward.setText(reposts_count+"");
            ((ImageViewHolder) holder).weibo_image_comment.setText(comments_count+"");
            ((ImageViewHolder) holder).weibo_image_thumbsup.setText(attitudes_count+"");
            ((ImageViewHolder) holder).weibo_image_checkbox.setChecked(favorited);

            List<ImageInfo> pic_urls = status.getPic_urls();
            NineGridView.setImageLoader(new ImageLoader.GlideImageLoader());
            ((ImageViewHolder) holder).weibo_image_ninegridimageview.setAdapter
                    (new NineGridViewClickAdapter(wcarbo.getContext(),pic_urls));
        } else if (holder instanceof TextViewHolder) {
            UsersBean usersBean = status.getUsersBean();
            String name = usersBean.getScreen_name();
            String avatar = usersBean.getAvatar_hd();
            Glide.with(mFragment)
                    .load(avatar)
                    .into(((TextViewHolder) holder).weibo_text_avatar);
            ((TextViewHolder) holder).weibo_text_name.setText(name);
            ((TextViewHolder) holder).weibo_text_time.setText(time);
            ((TextViewHolder) holder).weibo_text_source.setText(source);
            ((TextViewHolder) holder).weibo_text_contnet_text.setText(content);
            ((TextViewHolder) holder).weibo_text_forward.setText(reposts_count+"");
            ((TextViewHolder) holder).weibo_text_comment.setText(comments_count+"");
            ((TextViewHolder) holder).weibo_text_thumbsup.setText(attitudes_count+"");
            ((TextViewHolder) holder).weibo_text_collection.setChecked(favorited);
        } else if (holder instanceof RetweetedImageViewHolder){
            UsersBean usersBean = status.getUsersBean();
            String name = usersBean.getScreen_name();
            String avatar = usersBean.getAvatar_hd();
            Glide.with(mFragment)
                    .load(avatar)
                    .into(((RetweetedImageViewHolder) holder).retweeted_weibo_iamge_avatar);
            ((RetweetedImageViewHolder) holder).retweeted_weibo_iamge_name.setText(name);
            ((RetweetedImageViewHolder) holder).retweeted_weibo_iamge_time.setText(time);
            ((RetweetedImageViewHolder) holder).retweeted_weibo_iamge_source.setText(source);
            ((RetweetedImageViewHolder) holder).retweeted_weibo_iamge_contnet_text.setText(content);
            ((RetweetedImageViewHolder) holder).retweeted_weibo_iamge_forward.setText(reposts_count+"");
            ((RetweetedImageViewHolder) holder).retweeted_weibo_iamge_comment.setText(comments_count+"");
            ((RetweetedImageViewHolder) holder).retweeted_weibo_iamge_thumbsup.setText(attitudes_count+"");
            ((RetweetedImageViewHolder) holder).retweeted_weibo_iamge_collection.setChecked(favorited);

            Status re_status = status.getRetweeted_status();
            String retweeted_time = re_status.getCreated_at();
            String retweeted_text = re_status.getText();
            String retweeted_source = StringUtil.getWeiboSource(re_status.getSource()) ;
            Boolean retweeted_favorited = re_status.isFavorited();
            int retweeted_reposts_count = re_status.getReposts_count();
            int retweeted_comments_count = re_status.getComments_count();
            int retweeted_attiudes_count = re_status.getAttitudes_count();
            List<ImageInfo> re_pic_urls = re_status.getPic_urls();

            UsersBean re_usersBean = re_status.getUsersBean();
            String retweeted_name = re_usersBean.getScreen_name();
            String retweeted_avatar = re_usersBean.getAvatar_hd();

            Glide.with(mFragment)
                    .load(retweeted_avatar)
                    .into(((RetweetedImageViewHolder) holder).weibo_iamge_avatar_retweeted);
            ((RetweetedImageViewHolder) holder).weibo_iamge_name_retweeted.setText(retweeted_name);
            ((RetweetedImageViewHolder) holder).weibo_iamge_time_retweeted.setText(retweeted_time);
            ((RetweetedImageViewHolder) holder).weibo_iamge_source_retweeted.setText(retweeted_source);
            ((RetweetedImageViewHolder) holder).weibo_iamge_contnet_text_retweeted.setText(retweeted_text);
            ((RetweetedImageViewHolder) holder).weibo_iamge_forward_retweeted.setText(retweeted_reposts_count + "");
            ((RetweetedImageViewHolder) holder).weibo_iamge_comment_retweeted.setText(retweeted_comments_count + "");
            ((RetweetedImageViewHolder) holder).weibo_iamge_thumbsup_retweeted.setText(retweeted_attiudes_count+"");
            NineGridView.setImageLoader(new ImageLoader.GlideImageLoader());
            ((RetweetedImageViewHolder) holder).weibo_image_ninegridimageview_retweeted.setAdapter
                    (new NineGridViewClickAdapter(wcarbo.getContext(),re_pic_urls));
        } else if (holder instanceof RetwwetedTextViewHolder){
            UsersBean usersBean = status.getUsersBean();
            String name = usersBean.getScreen_name();
            String avatar = usersBean.getAvatar_hd();
            Glide.with(mFragment)
                    .load(avatar)
                    .into(((RetwwetedTextViewHolder) holder).retweeted_weibo_text_avatar);
            ((RetwwetedTextViewHolder) holder).retweeted_weibo_text_name.setText(name);
            ((RetwwetedTextViewHolder) holder).retweeted_weibo_text_time.setText(time);
            ((RetwwetedTextViewHolder) holder).retweeted_weibo_text_source.setText(source);
            ((RetwwetedTextViewHolder) holder).retweeted_weibo_text_contnet_text.setText(content);
            ((RetwwetedTextViewHolder) holder).retweeted_weibo_text_forward.setText(reposts_count+"");
            ((RetwwetedTextViewHolder) holder).retweeted_weibo_text_comment.setText(comments_count+"");
            ((RetwwetedTextViewHolder) holder).retweeted_weibo_text_thumbsup.setText(attitudes_count+"");
            ((RetwwetedTextViewHolder) holder).retweeted_weibo_text_collection.setChecked(favorited);

            Status re_status = status.getRetweeted_status();
            String retweeted_time = re_status.getCreated_at();
            String retweeted_text = re_status.getText();
            String retweeted_source = StringUtil.getWeiboSource(re_status.getSource()) ;
            Boolean retweeted_favorited = re_status.isFavorited();
            int retweeted_reposts_count = re_status.getReposts_count();
            int retweeted_comments_count = re_status.getComments_count();
            int retweeted_attiudes_count = re_status.getAttitudes_count();

            UsersBean re_usersBean = re_status.getUsersBean();
            String retweeted_name = re_usersBean.getScreen_name();
            String retweeted_avatar = re_usersBean.getAvatar_hd();

            Glide.with(mFragment)
                    .load(retweeted_avatar)
                    .into(((RetwwetedTextViewHolder) holder).weibo_text_avatar_retweeted);
            ((RetwwetedTextViewHolder) holder).weibo_text_name_retweeted.setText(retweeted_name);
            ((RetwwetedTextViewHolder) holder).weibo_text_time_retweeted.setText(retweeted_time);
            ((RetwwetedTextViewHolder) holder).weibo_text_source_retweeted.setText(retweeted_source);
            ((RetwwetedTextViewHolder) holder).weibo_text_contnet_text_retweeted.setText(retweeted_text);
            ((RetwwetedTextViewHolder) holder).weibo_text_forward_retweeted.setText(retweeted_reposts_count+"");
            ((RetwwetedTextViewHolder) holder).weibo_text_comment_retweeted.setText(retweeted_comments_count+"");
            ((RetwwetedTextViewHolder) holder).weibo_text_thumbsup_retweeted.setText(retweeted_attiudes_count+"");
        } else if (holder instanceof DeletedViewHolder){
            ((DeletedViewHolder) holder).deleted_text.setText(content);
        } else if (holder instanceof RetweetedDeletedViewHolder){
            UsersBean usersBean = status.getUsersBean();
            String name = usersBean.getScreen_name();
            String avatar = usersBean.getAvatar_hd();
            Glide.with(mFragment)
                    .load(avatar)
                    .into(((RetweetedDeletedViewHolder) holder).retweeted_weibo_deleted_avatar);
            ((RetweetedDeletedViewHolder) holder).retweeted_weibo_deleted_name.setText(name);
            ((RetweetedDeletedViewHolder) holder).retweeted_weibo_deleted_time.setText(time);
            ((RetweetedDeletedViewHolder) holder).retweeted_weibo_deleted_source.setText(source);
            ((RetweetedDeletedViewHolder) holder).retweeted_weibo_deleted_contnet_text.setText(content);
            ((RetweetedDeletedViewHolder) holder).retweeted_weibo_deleted_forward.setText(reposts_count+"");
            ((RetweetedDeletedViewHolder) holder).retweeted_weibo_deleted_comment.setText(comments_count+"");
            ((RetweetedDeletedViewHolder) holder).retweeted_weibo_deleted_thumbsup.setText(attitudes_count+"");
            ((RetweetedDeletedViewHolder) holder).retweeted_weibo_deleted_collection.setChecked(favorited);

            Status re_status = status.getRetweeted_status();
            String retweeted_text = re_status.getText();
            ((RetweetedDeletedViewHolder) holder).weibo_deleted_text_retweeted.setText(retweeted_text);
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    protected void userpage(int position) {
    }

    protected void re_userpage(int position) {
    }

    protected Status PositionStatus(int position){
        return null;
    }

}
