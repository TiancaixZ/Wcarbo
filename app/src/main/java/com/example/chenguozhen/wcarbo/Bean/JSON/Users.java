package com.example.chenguozhen.wcarbo.Bean.JSON;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by chenguozhen on 2017/12/20.
 */

public class Users extends DataSupport {
    private int id;
    private String idstr;
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
    private String created_at;
    private String avatar_hd;
    private String verified_reason;
    private Boolean verified;

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public int getFrindes_count() {
        return frindes_count;
    }

    public void setFrindes_count(int frindes_count) {
        this.frindes_count = frindes_count;
    }

    public int getId() {
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
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
}
