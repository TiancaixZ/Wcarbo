package com.example.chenguozhen.wcarbo.Adapter.RecyclerViewAdapter;

import android.support.v4.app.Fragment;

import com.example.chenguozhen.wcarbo.Adapter.BaseAdapter;
import com.example.chenguozhen.wcarbo.Bean.Comments;
import com.example.chenguozhen.wcarbo.Bean.JSON.Comment;

import java.util.List;

/**
 * Created by chenguozhen on 2018/1/19.
 */

public class comment_list_adapter extends BaseAdapter<Comments> {


    public comment_list_adapter(List<Comments> DataList, Fragment fragment) {
        super(DataList, fragment);
    }

    @Override
    protected Comment PositionComment(int position) {
        return null;
    }
}
