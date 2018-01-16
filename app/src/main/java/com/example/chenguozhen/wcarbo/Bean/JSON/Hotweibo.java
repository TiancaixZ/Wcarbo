package com.example.chenguozhen.wcarbo.Bean.JSON;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguozhen on 2017/12/28.
 */

public class Hotweibo extends DataSupport implements Serializable{

    private String idstr;
    private String created_at;
    private String text;
    private String source;
    private boolean favorited;
    private boolean truncated;
    private int Pic_urls_count;
    private int reposts_count;
    private int comments_count;
    private int attitudes_count;
    private String Users_idstr;
    private String screen_name;
    private String location;
    private String description;
    private String url;
    private String profile_iamge_url;
    private String profile_url;
    private String gender;
    private int frindes_count;
    private int foolowers_count;
    private int statuses_count;
    private int favourites_count;
    private String Users_created_at;
    private String avatar_hd;
    private String verified_reason;
    private Boolean verified;

    private List<Hotweibo_Pic_urls> pic_urlsList = new ArrayList<Hotweibo_Pic_urls>();

    public List<Hotweibo_Pic_urls> getPic_urlsList() {
        return pic_urlsList;
    }

    public void setPic_urlsList(List<Hotweibo_Pic_urls> pic_urlsList) {
        this.pic_urlsList = pic_urlsList;
    }

    public String getUsers_idstr() {
        return Users_idstr;
    }

    public void setUsers_idstr(String users_idstr) {
        Users_idstr = users_idstr;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProfile_iamge_url() {
        return profile_iamge_url;
    }

    public void setProfile_iamge_url(String profile_iamge_url) {
        this.profile_iamge_url = profile_iamge_url;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getFrindes_count() {
        return frindes_count;
    }

    public void setFrindes_count(int frindes_count) {
        this.frindes_count = frindes_count;
    }

    public int getFoolowers_count() {
        return foolowers_count;
    }

    public void setFoolowers_count(int foolowers_count) {
        this.foolowers_count = foolowers_count;
    }

    public int getStatuses_count() {
        return statuses_count;
    }

    public void setStatuses_count(int statuses_count) {
        this.statuses_count = statuses_count;
    }

    public int getFavourites_count() {
        return favourites_count;
    }

    public void setFavourites_count(int favourites_count) {
        this.favourites_count = favourites_count;
    }

    public String getUsers_created_at() {
        return Users_created_at;
    }

    public void setUsers_created_at(String users_created_at) {
        Users_created_at = users_created_at;
    }

    public String getAvatar_hd() {
        return avatar_hd;
    }

    public void setAvatar_hd(String avatar_hd) {
        this.avatar_hd = avatar_hd;
    }

    public String getVerified_reason() {
        return verified_reason;
    }

    public void setVerified_reason(String verified_reason) {
        this.verified_reason = verified_reason;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getIdstr() {
        return idstr;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    public int getReposts_count() {
        return reposts_count;
    }

    public void setReposts_count(int reposts_count) {
        this.reposts_count = reposts_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getAttitudes_count() {
        return attitudes_count;
    }

    public void setAttitudes_count(int attitudes_count) {
        this.attitudes_count = attitudes_count;
    }

    public int getPic_urls_count() {
        return Pic_urls_count;
    }

    public void setPic_urls_count(int pic_urls_count) {
        Pic_urls_count = pic_urls_count;
    }
}
