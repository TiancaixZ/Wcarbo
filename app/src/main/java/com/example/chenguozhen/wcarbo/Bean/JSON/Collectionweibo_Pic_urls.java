package com.example.chenguozhen.wcarbo.Bean.JSON;

import com.example.chenguozhen.wcarbo.Bean.JSON.Collectionweibo;

import org.litepal.crud.DataSupport;

/**
 * Created by chenguozhen on 2018/1/3.
 */

public class Collectionweibo_Pic_urls extends DataSupport{
    private String thumbnail_pic;
    private String retweedted_pic;
    private String Collectionweibo_idstr;
    private Collectionweibo collectionweibo;

    public String getCollectionweibo_idstr() {
        return Collectionweibo_idstr;
    }

    public String getRetweedted_pic() {
        return retweedted_pic;
    }

    public void setRetweedted_pic(String retweedted_pic) {
        this.retweedted_pic = retweedted_pic;
    }

    public void setCollectionweibo_idstr(String collectionweibo_idstr) {
        Collectionweibo_idstr = collectionweibo_idstr;
    }

    public String getThumbnail_pic() {
        return thumbnail_pic;
    }

    public void setThumbnail_pic(String thumbnail_pic) {
        this.thumbnail_pic = thumbnail_pic;
    }

    public Collectionweibo getCollectionweibo() {
        return collectionweibo;
    }

    public void setCollectionweibo(Collectionweibo collectionweibo) {
        this.collectionweibo = collectionweibo;
    }
}
