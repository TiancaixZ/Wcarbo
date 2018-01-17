package com.example.chenguozhen.wcarbo.Bean;

import com.example.chenguozhen.wcarbo.Bean.Gson.UsersBean;
import com.lzy.ninegrid.ImageInfo;

import java.util.List;

/**
 * Created by chenguozhen on 2018/1/16.
 */

public class Status {
    private String created_at;
    private long id;
    private String idstr;
    private String text;
    private String source;
    private boolean favorited;
    private boolean truncated;
    private int reposts_count;
    private int comments_count;
    private int attitudes_count;
    private List<ImageInfo> pic_urls;
    private UsersBean usersBean;
    private Status retweeted_status;

    private boolean deleted;
    private boolean photo;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isPhoto() {
        return photo;
    }

    public void setPhoto(boolean photo) {
        this.photo = photo;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<ImageInfo> getPic_urls() {
        return pic_urls;
    }

    public void setPic_urls(List<ImageInfo> pic_urls) {
        this.pic_urls = pic_urls;
    }

    public UsersBean getUsersBean() {
        return usersBean;
    }

    public void setUsersBean(UsersBean usersBean) {
        this.usersBean = usersBean;
    }

    public Status getRetweeted_status() {
        return retweeted_status;
    }

    public void setRetweeted_status(Status retweeted_status) {
        this.retweeted_status = retweeted_status;
    }
}
