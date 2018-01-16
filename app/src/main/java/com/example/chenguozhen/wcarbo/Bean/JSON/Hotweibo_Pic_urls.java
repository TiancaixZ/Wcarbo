package com.example.chenguozhen.wcarbo.Bean.JSON;

import com.example.chenguozhen.wcarbo.Bean.JSON.Hotweibo;

import org.litepal.crud.DataSupport;


/**
 * Created by chenguozhen on 2017/12/30.
 */

public class Hotweibo_Pic_urls extends DataSupport {
    private String thumbnail_pic;
    private String Hotweibo_idstr;
    private Hotweibo hotweibo;

    public String getThumbnail_pic() {
        return thumbnail_pic;
    }

    public void setThumbnail_pic(String thumbnail_pic) {
        this.thumbnail_pic = thumbnail_pic;
    }

    public String getHotweibo_idstr() {
        return Hotweibo_idstr;
    }

    public void setHotweibo_idstr(String hotweibo_idstr) {
        Hotweibo_idstr = hotweibo_idstr;
    }

    public Hotweibo getHotweibo() {
        return hotweibo;
    }

    public void setHotweibo(Hotweibo hotweibo) {
        this.hotweibo = hotweibo;
    }
}
