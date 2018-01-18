package com.example.chenguozhen.wcarbo.Bean;

import com.example.chenguozhen.wcarbo.Bean.JSON.Status;

import java.util.List;

/**
 * Created by chenguozhen on 2018/1/16.
 */

public class Favorites {
    private int total_number;
    private List<FavoritesBean> favorites;

    public static class FavoritesBean {
        private Status status;
        private String favorited_time;

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public String getFavorited_time() {
            return favorited_time;
        }

        public void setFavorited_time(String favorited_time) {
            this.favorited_time = favorited_time;
        }
    }

    public int getTotal_number() {
        return total_number;
    }

    public void setTotal_number(int total_number) {
        this.total_number = total_number;
    }

    public List<FavoritesBean> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<FavoritesBean> favorites) {
        this.favorites = favorites;
    }
}
