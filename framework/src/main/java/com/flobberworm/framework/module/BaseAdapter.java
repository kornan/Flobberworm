package com.flobberworm.framework.module;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * flobberworm team
 * Created by kornan on 2018/1/9.
 */

public abstract class BaseAdapter<V extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<V> {
    private List<T> dataList;

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }


    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }
}
