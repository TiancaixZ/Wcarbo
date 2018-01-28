package com.example.chenguozhen.wcarbo.data;

import com.example.chenguozhen.wcarbo.Bean.JSON.Status;

import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.SaveCallback;

import java.util.List;

/**
 * Created by chenguozhen on 2018/1/27.
 */

public class TimeLIneData implements Database<Status>{

    @Override
    public void onLoad(List<Status> statuses) {
        DataSupport.deleteAll(Status.class);
        DataSupport.saveAllAsync(statuses).listen(new SaveCallback() {
            @Override
            public void onFinish(boolean success) {

            }
        });
    }

    @Override
    public void update(List<Status> statuses) {

    }

    @Override
    public List<Status> query() {
        List<Status> allStatus = DataSupport.findAll(Status.class);
        return allStatus;
    }
}
