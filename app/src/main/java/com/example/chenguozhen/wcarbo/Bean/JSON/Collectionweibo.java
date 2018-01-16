package com.example.chenguozhen.wcarbo.Bean.JSON;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguozhen on 2018/1/2.
 */

public class Collectionweibo extends DataSupport implements Serializable{
    private String created_at;
    private String idstr;
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

    private Boolean retweeted;
    private String retweeted_created_at;
    private String retweeted_idstr;
    private String retweeted_text;
    private String retweeted_source;
    private boolean retweeted_favorited;
    private boolean retweeted_truncated;
    private int retweeted_Pic_urls_count;
    private int retweeted_reposts_count;
    private int retweeted_comments_count;
    private int retweeted_attitudes_count;
    private String retweeted_Users_idstr;
    private String retweeted_screen_name;
    private String retweeted_location;
    private String retweeted_description;
    private String retweeted_url;
    private String retweeted_profile_iamge_url;
    private String retweeted_profile_url;
    private String retweeted_gender;
    private int retweeted_frindes_count;
    private int retweeted_foolowers_count;
    private int retweeted_statuses_count;
    private int retweeted_favourites_count;
    private String retweeted_Users_created_at;
    private String retweeted_avatar_hd;
    private String retweeted_verified_reason;
    private Boolean retweeted_verified;

    private List<Collectionweibo_Pic_urls> pic_urlsList = new ArrayList<Collectionweibo_Pic_urls>();

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public int getPic_urls_count() {
        return Pic_urls_count;
    }

    public void setPic_urls_count(int pic_urls_count) {
        Pic_urls_count = pic_urls_count;
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

    public Boolean getRetweeted() {
        return retweeted;
    }

    public void setRetweeted(Boolean retweeted) {
        this.retweeted = retweeted;
    }

    public String getRetweeted_created_at() {
        return retweeted_created_at;
    }

    public void setRetweeted_created_at(String retweeted_created_at) {
        this.retweeted_created_at = retweeted_created_at;
    }

    public String getRetweeted_idstr() {
        return retweeted_idstr;
    }

    public void setRetweeted_idstr(String retweeted_idstr) {
        this.retweeted_idstr = retweeted_idstr;
    }

    public String getRetweeted_text() {
        return retweeted_text;
    }

    public void setRetweeted_text(String retweeted_text) {
        this.retweeted_text = retweeted_text;
    }

    public String getRetweeted_source() {
        return retweeted_source;
    }

    public void setRetweeted_source(String retweeted_source) {
        this.retweeted_source = retweeted_source;
    }

    public boolean isRetweeted_favorited() {
        return retweeted_favorited;
    }

    public void setRetweeted_favorited(boolean retweeted_favorited) {
        this.retweeted_favorited = retweeted_favorited;
    }

    public boolean isRetweeted_truncated() {
        return retweeted_truncated;
    }

    public void setRetweeted_truncated(boolean retweeted_truncated) {
        this.retweeted_truncated = retweeted_truncated;
    }

    public int getRetweeted_Pic_urls_count() {
        return retweeted_Pic_urls_count;
    }

    public void setRetweeted_Pic_urls_count(int retweeted_Pic_urls_count) {
        this.retweeted_Pic_urls_count = retweeted_Pic_urls_count;
    }

    public int getRetweeted_reposts_count() {
        return retweeted_reposts_count;
    }

    public void setRetweeted_reposts_count(int retweeted_reposts_count) {
        this.retweeted_reposts_count = retweeted_reposts_count;
    }

    public int getRetweeted_comments_count() {
        return retweeted_comments_count;
    }

    public void setRetweeted_comments_count(int retweeted_comments_count) {
        this.retweeted_comments_count = retweeted_comments_count;
    }

    public int getRetweeted_attitudes_count() {
        return retweeted_attitudes_count;
    }

    public void setRetweeted_attitudes_count(int retweeted_attitudes_count) {
        this.retweeted_attitudes_count = retweeted_attitudes_count;
    }

    public String getRetweeted_Users_idstr() {
        return retweeted_Users_idstr;
    }

    public void setRetweeted_Users_idstr(String retweeted_Users_idstr) {
        this.retweeted_Users_idstr = retweeted_Users_idstr;
    }

    public String getRetweeted_screen_name() {
        return retweeted_screen_name;
    }

    public void setRetweeted_screen_name(String retweeted_screen_name) {
        this.retweeted_screen_name = retweeted_screen_name;
    }

    public String getRetweeted_location() {
        return retweeted_location;
    }

    public void setRetweeted_location(String retweeted_location) {
        this.retweeted_location = retweeted_location;
    }

    public String getRetweeted_description() {
        return retweeted_description;
    }

    public void setRetweeted_description(String retweeted_description) {
        this.retweeted_description = retweeted_description;
    }

    public String getRetweeted_url() {
        return retweeted_url;
    }

    public void setRetweeted_url(String retweeted_url) {
        this.retweeted_url = retweeted_url;
    }

    public String getRetweeted_profile_iamge_url() {
        return retweeted_profile_iamge_url;
    }

    public void setRetweeted_profile_iamge_url(String retweeted_profile_iamge_url) {
        this.retweeted_profile_iamge_url = retweeted_profile_iamge_url;
    }

    public String getRetweeted_profile_url() {
        return retweeted_profile_url;
    }

    public void setRetweeted_profile_url(String retweeted_profile_url) {
        this.retweeted_profile_url = retweeted_profile_url;
    }

    public String getRetweeted_gender() {
        return retweeted_gender;
    }

    public void setRetweeted_gender(String retweeted_gender) {
        this.retweeted_gender = retweeted_gender;
    }

    public int getRetweeted_frindes_count() {
        return retweeted_frindes_count;
    }

    public void setRetweeted_frindes_count(int retweeted_frindes_count) {
        this.retweeted_frindes_count = retweeted_frindes_count;
    }

    public int getRetweeted_foolowers_count() {
        return retweeted_foolowers_count;
    }

    public void setRetweeted_foolowers_count(int retweeted_foolowers_count) {
        this.retweeted_foolowers_count = retweeted_foolowers_count;
    }

    public int getRetweeted_statuses_count() {
        return retweeted_statuses_count;
    }

    public void setRetweeted_statuses_count(int retweeted_statuses_count) {
        this.retweeted_statuses_count = retweeted_statuses_count;
    }

    public int getRetweeted_favourites_count() {
        return retweeted_favourites_count;
    }

    public void setRetweeted_favourites_count(int retweeted_favourites_count) {
        this.retweeted_favourites_count = retweeted_favourites_count;
    }

    public String getRetweeted_Users_created_at() {
        return retweeted_Users_created_at;
    }

    public void setRetweeted_Users_created_at(String retweeted_Users_created_at) {
        this.retweeted_Users_created_at = retweeted_Users_created_at;
    }

    public String getRetweeted_avatar_hd() {
        return retweeted_avatar_hd;
    }

    public void setRetweeted_avatar_hd(String retweeted_avatar_hd) {
        this.retweeted_avatar_hd = retweeted_avatar_hd;
    }

    public String getRetweeted_verified_reason() {
        return retweeted_verified_reason;
    }

    public void setRetweeted_verified_reason(String retweeted_verified_reason) {
        this.retweeted_verified_reason = retweeted_verified_reason;
    }

    public Boolean getRetweeted_verified() {
        return retweeted_verified;
    }

    public void setRetweeted_verified(Boolean retweeted_verified) {
        this.retweeted_verified = retweeted_verified;
    }

    public List<Collectionweibo_Pic_urls> getPic_urlsList() {
        return pic_urlsList;
    }

    public void setPic_urlsList(List<Collectionweibo_Pic_urls> pic_urlsList) {
        this.pic_urlsList = pic_urlsList;
    }

}
