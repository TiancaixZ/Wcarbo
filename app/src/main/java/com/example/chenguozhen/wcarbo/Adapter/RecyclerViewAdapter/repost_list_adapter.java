package com.example.chenguozhen.wcarbo.Adapter.RecyclerViewAdapter;

import android.support.v4.app.Fragment;

import com.example.chenguozhen.wcarbo.Adapter.CommentReportAdapter;
import com.example.chenguozhen.wcarbo.Bean.JSON.Status;
import com.example.chenguozhen.wcarbo.Interface.RecyclerViewItemClickLisntner;

import java.util.List;

/**
 * Created by chenguozhen on 2018/1/24.
 */

public class repost_list_adapter extends CommentReportAdapter<Status>{
    private List<Status> mDataList;

    public repost_list_adapter(List<Status> DataList, Fragment Fragment,RecyclerViewItemClickLisntner lisntner) {
        super(DataList, Fragment,lisntner);
        this.mDataList = DataList;
    }


    @Override
    protected Status status(int position) {
        Status status = mDataList.get(position);
        return status;
    }
}
