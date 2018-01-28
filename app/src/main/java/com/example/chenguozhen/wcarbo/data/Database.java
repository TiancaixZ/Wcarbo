package com.example.chenguozhen.wcarbo.data;

import java.util.List;

/**
 * Created by chenguozhen on 2018/1/9.
 */

public interface Database<T> {

    void onLoad(List<T> ts);

    void update(List<T> ts);

    List<T> query();
}
