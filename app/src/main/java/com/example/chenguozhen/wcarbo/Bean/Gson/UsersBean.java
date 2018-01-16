package com.example.chenguozhen.wcarbo.Bean.Gson;

import java.io.Serializable;

/**
 * Created by chenguozhen on 2018/1/13.
 */

public class UsersBean implements Serializable {

    /**
     * id : 2091368731
     * idstr : 2091368731
     * class : 1
     * screen_name : 赵满仓爱洗澡
     * name : 赵满仓爱洗澡
     * province : 100
     * city : 1000
     * location : 其他
     * description : 微博气人博主
     * url :
     * profile_image_url : http://tvax2.sinaimg.cn/crop.127.4.379.379.50/7ca7c11bly8fe7k9865afj20iz0ao7c8.jpg
     * cover_image : http://ww3.sinaimg.cn/crop.0.0.920.300/7ca7c11bgw1f4le67nxr9j20pk08cwfv.jpg
     * cover_image_phone : http://wx2.sinaimg.cn/crop.0.0.640.640.640/7ca7c11bly1fejkjchjz8j20hs0h0ac8.jpg;http://wx1.sinaimg.cn/crop.0.0.640.640.640/7ca7c11bly1fe6rajins3j20sg0sgwln.jpg;http://wx1.sinaimg.cn/crop.0.0.640.640.640/7ca7c11bly1fddokdq2qij20j80j876j.jpg;http://ww1.sinaimg.cn/crop.0.0.640.640.640/9d44112bjw1f1xl1c10tuj20hs0hs0tw.jpg
     * profile_url : u/2091368731
     * domain :
     * weihao :
     * gender : m
     * followers_count : 13082
     * friends_count : 247
     * pagefriends_count : 5
     * statuses_count : 1480
     * favourites_count : 2
     * created_at : Sun Apr 17 08:38:28 +0800 2011
     * following : true
     * allow_all_act_msg : false
     * geo_enabled : false
     * verified : true
     * verified_type : 0
     * remark :
     * insecurity : {"sexual_content":false}
     * status_id : 4194765932933444
     * ptype : 0
     * allow_all_comment : true
     * avatar_large : http://tvax2.sinaimg.cn/crop.127.4.379.379.180/7ca7c11bly8fe7k9865afj20iz0ao7c8.jpg
     * avatar_hd : http://tvax2.sinaimg.cn/crop.127.4.379.379.1024/7ca7c11bly8fe7k9865afj20iz0ao7c8.jpg
     * verified_reason : 搞笑幽默博主
     * verified_trade :
     * verified_reason_url :
     * verified_source :
     * verified_source_url :
     * verified_state : 0
     * verified_level : 3
     * verified_type_ext : 0
     * has_service_tel : false
     * verified_reason_modified :
     * verified_contact_name :
     * verified_contact_email :
     * verified_contact_mobile :
     * follow_me : false
     * like : false
     * like_me : false
     * online_status : 0
     * bi_followers_count : 21
     * lang : zh-cn
     * star : 0
     * mbtype : 12
     * mbrank : 4
     * block_word : 0
     * block_app : 1
     * credit_score : 80
     * user_ability : 256
     * avatargj_id : gj_vip_054
     * urank : 14
     * story_read_state : -1
     * vclub_member : 0
     * ability_tags : 社交,移动应用,电商,房地产,IT服务
     * cardid : star_020
     */

    private long id;
    private String idstr;
    private String screen_name;
    private String name;
    private String location;
    private String description;
    private String profile_image_url;
    private String cover_image_phone;
    private String profile_url;
    private String gender;
    private int followers_count;
    private int friends_count;
    private int pagefriends_count;
    private int statuses_count;
    private int favourites_count;
    private String created_at;
    private boolean following;
    private boolean allow_all_act_msg;
    private boolean geo_enabled;
    private boolean verified;
    private boolean allow_all_comment;
    private String avatar_large;
    private String avatar_hd;
    private String verified_reason;
    private boolean follow_me;
    private boolean like;
    private boolean like_me;
    private int online_status;
    private int bi_followers_count;
    private String lang;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdstr() {
        return idstr;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public String getCover_image_phone() {
        return cover_image_phone;
    }

    public void setCover_image_phone(String cover_image_phone) {
        this.cover_image_phone = cover_image_phone;
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

    public int getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public int getFriends_count() {
        return friends_count;
    }

    public void setFriends_count(int friends_count) {
        this.friends_count = friends_count;
    }

    public int getPagefriends_count() {
        return pagefriends_count;
    }

    public void setPagefriends_count(int pagefriends_count) {
        this.pagefriends_count = pagefriends_count;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public boolean isAllow_all_act_msg() {
        return allow_all_act_msg;
    }

    public void setAllow_all_act_msg(boolean allow_all_act_msg) {
        this.allow_all_act_msg = allow_all_act_msg;
    }

    public boolean isGeo_enabled() {
        return geo_enabled;
    }

    public void setGeo_enabled(boolean geo_enabled) {
        this.geo_enabled = geo_enabled;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }


    public boolean isAllow_all_comment() {
        return allow_all_comment;
    }

    public void setAllow_all_comment(boolean allow_all_comment) {
        this.allow_all_comment = allow_all_comment;
    }

    public String getAvatar_large() {
        return avatar_large;
    }

    public void setAvatar_large(String avatar_large) {
        this.avatar_large = avatar_large;
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

    public boolean isFollow_me() {
        return follow_me;
    }

    public void setFollow_me(boolean follow_me) {
        this.follow_me = follow_me;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public boolean isLike_me() {
        return like_me;
    }

    public void setLike_me(boolean like_me) {
        this.like_me = like_me;
    }

    public int getOnline_status() {
        return online_status;
    }

    public void setOnline_status(int online_status) {
        this.online_status = online_status;
    }

    public int getBi_followers_count() {
        return bi_followers_count;
    }

    public void setBi_followers_count(int bi_followers_count) {
        this.bi_followers_count = bi_followers_count;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

}
