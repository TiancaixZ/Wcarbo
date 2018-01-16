package com.example.chenguozhen.wcarbo.Fragment_weibo_collection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.chenguozhen.wcarbo.Bean.Favorites;
import com.example.chenguozhen.wcarbo.Bean.JSON.Collectionweibo;
import com.example.chenguozhen.wcarbo.Bean.JSON.Collectionweibo_Pic_urls;
import com.example.chenguozhen.wcarbo.Bean.Gson.UsersBean;
import com.example.chenguozhen.wcarbo.Bean.Status;
import com.example.chenguozhen.wcarbo.Constants;
import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.RecyclerView.Holder.ImageViewHolder;
import com.example.chenguozhen.wcarbo.RecyclerView.Holder.RetweetedImageViewHolder;
import com.example.chenguozhen.wcarbo.RecyclerView.Holder.RetwwetedTextViewHolder;
import com.example.chenguozhen.wcarbo.RecyclerView.Holder.TextViewHolder;
import com.example.chenguozhen.wcarbo.activity.ClickButtonActivity;
import com.example.chenguozhen.wcarbo.utils.ImageLoader;
import com.example.chenguozhen.wcarbo.utils.StringUtil;
import com.example.chenguozhen.wcarbo.utils.Utility;
import com.example.chenguozhen.wcarbo.wcarbo;
import com.google.gson.Gson;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by chenguozhen on 2018/1/2.
 */

public class collection_list_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Collectionweibo> mcollectionweiboList;
    private List<Collectionweibo_Pic_urls> mPic_urls;

    private List<Favorites.FavoritesBean> mfavoritesBeans;

    private static String url = "https://api.weibo.com/2/users/show.json?source=3867086258";

    private Fragment mFragment;

    private final int IMAGEVIEWTYPE = 1;
    private final int TEXTVIEWTYPE = 2;
    private final int RETWEETEDIMAGEVIEWYPTE = 3;
    private final int RETWEETEDTEXTVIEWYTPE = 4;

    public collection_list_adapter(List<Favorites.FavoritesBean> favoritesBeans, Fragment fragment){
        //this.mcollectionweiboList = collectionweiboList;
        //this.mPic_urls = pic_urls;
        this.mFragment = fragment;

        this.mfavoritesBeans = favoritesBeans;
    }

    @Override
    public int getItemViewType(int position) {
        //Collectionweibo collectionweibo = mcollectionweiboList.get(position);

        Status status = mfavoritesBeans.get(position).getStatus();

        if (status.getRetweeted_status() == null){
            if (status.getPic_urls().size() > 0){
                return IMAGEVIEWTYPE;
            } else {
                return TEXTVIEWTYPE;
            }
        } else {
            if (status.getRetweeted_status().getPic_urls().size() > 0){
                return RETWEETEDIMAGEVIEWYPTE;
            } else {
                return RETWEETEDTEXTVIEWYTPE;
            }
        }

        /**
        if (collectionweibo.getRetweeted() == null){
            return TEXTVIEWTYPE;
        } else {
            if (collectionweibo.getRetweeted()){
                if (collectionweibo.getRetweeted_Pic_urls_count() == 0){
                    return RETWEETEDTEXTVIEWYTPE;
                } else {
                    return RETWEETEDIMAGEVIEWYPTE;
                }
            } else {
                if (collectionweibo.getPic_urls_count() == 0){
                    return TEXTVIEWTYPE;
                } else {
                    return IMAGEVIEWTYPE;
                }
            }
        }
         **/
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
                    init_userpager(position);
                }
            });
            holder.weibo_image_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    init_userpager(position);
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
                    init_userpager(position);
                }
            });
            holder.weibo_text_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    init_userpager(position);
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
                    init_userpager(position);
                }
            });
            holder.retweeted_weibo_iamge_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    init_userpager(position);
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
                    init_userpager(position);
                }
            });
            holder.retweeted_weibo_text_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    init_userpager(position);
                }
            });
            return holder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Collectionweibo positionCollectionweibo = mcollectionweiboList.get(position);



        String name = positionCollectionweibo.getScreen_name();
        String avatar = positionCollectionweibo.getAvatar_hd();
        String time = positionCollectionweibo.getCreated_at();
        String source = StringUtil.getWeiboSource(positionCollectionweibo.getSource());
        String content = positionCollectionweibo.getText();
        boolean favorited = positionCollectionweibo.isFavorited();
        boolean truncated = positionCollectionweibo.isTruncated();
        int reposts_count = positionCollectionweibo.getReposts_count();
        int comments_count = positionCollectionweibo.getComments_count();
        int attitudes_count = positionCollectionweibo.getAttitudes_count();

        Boolean retweeted = positionCollectionweibo.getRetweeted();
        String retweeted_time = positionCollectionweibo.getRetweeted_created_at();
        String retweeted_text = positionCollectionweibo.getRetweeted_text();
        String retweeted_source = positionCollectionweibo.getRetweeted_source();
        Boolean retweeted_favorited = positionCollectionweibo.getRetweeted_verified();
        int retweeted_reposts_count = positionCollectionweibo.getRetweeted_reposts_count();
        int retweeted_comments_count = positionCollectionweibo.getRetweeted_comments_count();
        int retweeted_attiudes_count = positionCollectionweibo.getRetweeted_attitudes_count();
        String retweeted_name = positionCollectionweibo.getRetweeted_screen_name();
        String retweeted_avatar = positionCollectionweibo.getRetweeted_avatar_hd();

        if (holder instanceof ImageViewHolder){
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


            List<ImageInfo> imageInfo = new ArrayList<ImageInfo>();
            for (int i = 0; i < mPic_urls.size(); i++) {
                if (positionCollectionweibo.getIdstr().equals(mPic_urls.get(i))){
                    ImageInfo imageInfo1 = new ImageInfo();
                    imageInfo1.setThumbnailUrl(mPic_urls.get(i).getThumbnail_pic());
                    imageInfo1.setBigImageUrl(mPic_urls.get(i).getThumbnail_pic());
                    imageInfo.add(imageInfo1);
                }
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
            ((TextViewHolder) holder).weibo_text_forward.setText(reposts_count+"");
            ((TextViewHolder) holder).weibo_text_comment.setText(comments_count+"");
            ((TextViewHolder) holder).weibo_text_thumbsup.setText(attitudes_count+"");
            ((TextViewHolder) holder).weibo_text_collection.setChecked(favorited);
        } else if (holder instanceof RetweetedImageViewHolder){
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
            List<ImageInfo> imageInfo = new ArrayList<ImageInfo>();
            for (int i = 0; i < mPic_urls.size(); i++) {
                if (positionCollectionweibo.getIdstr().equals(mPic_urls.get(i))){
                    ImageInfo imageInfo1 = new ImageInfo();
                    imageInfo1.setThumbnailUrl(mPic_urls.get(i).getRetweedted_pic());
                    imageInfo1.setBigImageUrl(mPic_urls.get(i).getRetweedted_pic());
                    imageInfo.add(imageInfo1);
                }
            }
            NineGridView.setImageLoader(new ImageLoader.GlideImageLoader());
            ((RetweetedImageViewHolder) holder).weibo_image_ninegridimageview_retweeted.setAdapter
                    (new NineGridViewClickAdapter(wcarbo.getContext(),imageInfo));
        } else if (holder instanceof  RetwwetedTextViewHolder){
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
        }
    }

    @Override
    public int getItemCount() {
        return mcollectionweiboList.size();
    }

    private void init_userpager(int position){
        String screen_name = mcollectionweiboList.get(position).getScreen_name();
        String token = ((wcarbo)mFragment.getActivity().getApplication()).getToken();
        OkHttpClient client = new OkHttpClient();
        client.newCall(Utility.usersBean(url,token,screen_name)).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                UsersBean usersBean = gson.fromJson(responseData,UsersBean.class);
                Intent intent = new Intent(wcarbo.getContext(),ClickButtonActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("com.example.chenguozhen.wcarbo.activity.NAV_FRIENDS", Constants.collection_usepager);
                bundle.putSerializable("UserBean",usersBean);
                intent.putExtras(bundle);
                mFragment.startActivity(intent);
            }
        });
    }
}
