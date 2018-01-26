package com.example.chenguozhen.wcarbo.Adapter.RecyclerViewAdapter;

import android.support.v4.app.Fragment;

import com.example.chenguozhen.wcarbo.Adapter.CommentReportAdapter;
import com.example.chenguozhen.wcarbo.Bean.JSON.Comment;

import java.util.List;

/**
 * Created by chenguozhen on 2018/1/23.
 */

public class comment_list_adapter extends CommentReportAdapter<Comment>{
    List<Comment> mDataList;

    public comment_list_adapter(List<Comment> DataList, Fragment Fragment) {
        super(DataList, Fragment);
        this.mDataList = DataList;
    }

    @Override
    protected Comment comment(int position) {
        Comment comment = mDataList.get(position);
        return comment;
    }
}
