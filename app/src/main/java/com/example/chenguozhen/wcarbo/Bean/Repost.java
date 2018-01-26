package com.example.chenguozhen.wcarbo.Bean;

import com.example.chenguozhen.wcarbo.Bean.Gson.error;
import com.example.chenguozhen.wcarbo.Bean.JSON.Status;

import java.util.List;

/**
 * Created by chenguozhen on 2018/1/24.
 */

public class Repost {

    private boolean hasvisible;
    private long previous_cursor;
    private long next_cursor;
    private int total_number;
    private List<Status> reposts;
    private error error;

    public error getError(){
        return error;
    }

    public void setError(error error){
        this.error = error;

    }

    public boolean isHasvisible() {
        return hasvisible;
    }

    public void setHasvisible(boolean hasvisible) {
        this.hasvisible = hasvisible;
    }

    public long getPrevious_cursor() {
        return previous_cursor;
    }

    public void setPrevious_cursor(long previous_cursor) {
        this.previous_cursor = previous_cursor;
    }

    public long getNext_cursor() {
        return next_cursor;
    }

    public void setNext_cursor(long next_cursor) {
        this.next_cursor = next_cursor;
    }

    public int getTotal_number() {
        return total_number;
    }

    public void setTotal_number(int total_number) {
        this.total_number = total_number;
    }

    public List<Status> getReposts() {
        return reposts;
    }

    public void setReposts(List<Status> reposts) {
        this.reposts = reposts;
    }
}
