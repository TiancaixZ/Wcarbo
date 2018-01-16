package com.example.chenguozhen.wcarbo.Fragment_weibo_hotpost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.RecyclerView.Holder.ImageViewHolder;
import com.example.chenguozhen.wcarbo.RecyclerView.Holder.TextViewHolder;
import com.example.chenguozhen.wcarbo.activity.ClickButtonActivity;
import com.example.chenguozhen.wcarbo.Bean.JSON.Hotweibo;
import com.example.chenguozhen.wcarbo.Bean.JSON.Hotweibo_Pic_urls;
import com.example.chenguozhen.wcarbo.utils.ImageLoader;
import com.example.chenguozhen.wcarbo.wcarbo;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguozhen on 2017/12/28.
 */

public class hotweibo_list_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Hotweibo> mHotweibos;

    private Fragment mFragment;

    private final int IMAGEVIEWTYPE = 0;
    private final int TEXTVIEWTYPE = 1;

    public hotweibo_list_adapter(List<Hotweibo> Hotweibos, Fragment fragment){
        this.mHotweibos = Hotweibos;
        this.mFragment = fragment;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHotweibos.get(position).getPic_urls_count() == 0){
            return TEXTVIEWTYPE;
        } else {
            return IMAGEVIEWTYPE;
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
                public void onClick(View view) {
                    intentusepaper(holder);
                }
            });
            holder.weibo_image_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intentusepaper(holder);
                }
            });
            return holder;
        } else if (viewType == TEXTVIEWTYPE){
            view = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.layout_fragment_weibo_text,parent,false);
            final TextViewHolder holder = new TextViewHolder(view);
            holder.weibo_text_avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intentusepaper(holder);
                }
            });
            holder.weibo_text_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intentusepaper(holder);
                }
            });
            return holder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Hotweibo positionHotweibo = mHotweibos.get(position);
        String avatar = positionHotweibo.getAvatar_hd();
        String name = positionHotweibo.getScreen_name();
        String time = positionHotweibo.getCreated_at();
        String source = positionHotweibo.getSource();
        String content = positionHotweibo.getText();
        int forward = positionHotweibo.getReposts_count();
        int comment = positionHotweibo.getComments_count();
        boolean thumbsup = positionHotweibo.isFavorited();
        if (holder instanceof ImageViewHolder){
            Glide.with(mFragment)
                    .load(avatar)
                    .into(((ImageViewHolder) holder).weibo_image_avatar);
            ((ImageViewHolder) holder).weibo_image_name.setText(name);
            ((ImageViewHolder) holder).weibo_image_time.setText(time);
            ((ImageViewHolder) holder).weibo_image_source.setText(source);
            ((ImageViewHolder) holder).weibo_image_contnet_text.setText(content);
            ((ImageViewHolder) holder).weibo_image_forward.setText(forward+"");
            ((ImageViewHolder) holder).weibo_image_comment.setText(comment+"");

            List<Hotweibo_Pic_urls> pic_urls = DataSupport.where
                    ("Hotweibo_idstr == ?", positionHotweibo.getIdstr()).find(Hotweibo_Pic_urls.class);
            List<ImageInfo> imageInfo = new ArrayList<ImageInfo>();
            for (int i = 0; i < pic_urls.size(); i++) {
                ImageInfo imageInfo1 = new ImageInfo();
                imageInfo1.setThumbnailUrl(pic_urls.get(i).getThumbnail_pic());
                imageInfo1.setBigImageUrl(pic_urls.get(i).getThumbnail_pic());
                imageInfo.add(imageInfo1);
            }
            NineGridView.setImageLoader(new ImageLoader.GlideImageLoader());
            ((ImageViewHolder) holder).weibo_image_ninegridimageview.setAdapter
                    (new NineGridViewClickAdapter(wcarbo.getContext(),imageInfo));
        } else if (holder instanceof TextViewHolder) {
            Glide.with(mFragment)
                    .load(avatar)
                    .into(((TextViewHolder) holder).weibo_text_avatar);
            ((TextViewHolder) holder).weibo_text_name.setText(name);
            ((TextViewHolder) holder).weibo_text_time.setText(time);
            ((TextViewHolder) holder).weibo_text_source.setText(source);
            ((TextViewHolder) holder).weibo_text_contnet_text.setText(content);
            ((TextViewHolder) holder).weibo_text_forward.setText(forward+"");
            ((TextViewHolder) holder).weibo_text_comment.setText(comment+"");
        }
    }

    @Override
    public int getItemCount() {
        return mHotweibos.size();
    }

    /** 打开usepager **/
    private void intentusepaper(RecyclerView.ViewHolder holder){
        int position = holder.getAdapterPosition();
        Hotweibo hotweibo = mHotweibos.get(position);
        Intent intent = new Intent(wcarbo.getContext(),ClickButtonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("com.example.chenguozhen.wcarbo.activity.NAV_FRIENDS", R.id.weibo_image_avatar);
        bundle.putSerializable("hotweibo",hotweibo);
        intent.putExtras(bundle);
        mFragment.startActivity(intent);
    }

}
