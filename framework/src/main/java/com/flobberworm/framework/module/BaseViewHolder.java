package com.flobberworm.framework.module;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * flobberworm team
 * Created by kornan on 2018/1/1.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(T data);
}
