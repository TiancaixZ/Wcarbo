package com.example.chenguozhen.wcarbo.data;

import com.example.chenguozhen.wcarbo.Bean.JSON.Collectionweibo_Pic_urls;

import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.SaveCallback;

import java.util.Iterator;
import java.util.List;

/**
 * Created by chenguozhen on 2018/1/10.
 */

public class Collection_Pic_urls_Data implements Database<Collectionweibo_Pic_urls>{

    @Override
    public void onLoad(List<Collectionweibo_Pic_urls> collectionweibo_pic_urls) {
        List<Collectionweibo_Pic_urls> load_list = collectionweibo_pic_urls;
        List<Collectionweibo_Pic_urls> database_list = DataSupport.findAll(Collectionweibo_Pic_urls.class);
        Iterator<Collectionweibo_Pic_urls> it = load_list.iterator();
        DataSupport.saveAllAsync(load_list).listen(new SaveCallback() {
            @Override
            public void onFinish(boolean success) {

            }
        });
    }

    @Override
    public void update(List<Collectionweibo_Pic_urls> collectionweibo_pic_urls) {

    }

    @Override
    public void query(List<Collectionweibo_Pic_urls> collectionweibo_pic_urls) {

    }
}
