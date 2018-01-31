package com.example.chenguozhen.wcarbo.Adapter.RecyclerViewAdapter;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.chenguozhen.wcarbo.Adapter.CommentReportAdapter;
import com.example.chenguozhen.wcarbo.Bean.JSON.Comment;
import com.example.chenguozhen.wcarbo.Interface.RecyclerViewItemClickLisntner;

import java.util.List;

/**
 * Created by chenguozhen on 2018/1/23.
 */

public class comment_list_adapter extends CommentReportAdapter<Comment>{
    List<Comment> mDataList;

    public comment_list_adapter(List<Comment> DataList, Fragment Fragment,RecyclerViewItemClickLisntner lisntner) {
        super(DataList, Fragment,lisntner);
        this.mDataList = DataList;
    }

    @Override
    protected Comment comment(int position) {
        Comment comment = null;
        if (mDataList.size() != 0){
           comment = mDataList.get(position);
        }
        return comment;
    }
}
