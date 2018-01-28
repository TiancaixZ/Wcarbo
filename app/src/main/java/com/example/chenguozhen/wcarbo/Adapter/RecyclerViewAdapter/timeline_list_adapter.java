package com.example.chenguozhen.wcarbo.Adapter.RecyclerViewAdapter;

import android.support.v4.app.Fragment;

import com.example.chenguozhen.wcarbo.Adapter.BaseListAdapter;
import com.example.chenguozhen.wcarbo.Bean.JSON.Status;

import java.util.List;

/**
 * Created by chenguozhen on 2018/1/27.
 */

public class timeline_list_adapter extends BaseListAdapter<Status>{
    private List<Status> mTimelineList;
    private Fragment mFragment;

    public timeline_list_adapter(List<Status> TimelineList, Fragment fragment) {
        super(TimelineList, fragment);
        this.mTimelineList = TimelineList;
        this.mFragment = fragment;
    }

    @Override
    protected Status PositionStatus(int position) {
        Status status = mTimelineList.get(position);
        return status;
    }
}
