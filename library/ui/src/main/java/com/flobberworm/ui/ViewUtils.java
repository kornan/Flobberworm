package com.flobberworm.ui;

import android.support.v7.widget.RecyclerView;

import com.flobberworm.load.ItemClickSupport;
import com.flobberworm.load.OnLoadMoreListener;
import com.flobberworm.load.RecyclerManager;

/**
 * ViewUtils
 * Created by Kornan on 2018/1/30.
 */

public final class ViewUtils {

//    public static void setColorScheme(SwipeRefreshLayout swipeRefreshLayout) {
//        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
//    }

    public static RecyclerManager setRecyclerManager(
            RecyclerView recyclerView,
            RecyclerView.Adapter adapter,
            RecyclerView.LayoutManager layoutManager,
            ItemClickSupport.OnItemClickListener onItemClickListener,
            OnLoadMoreListener onLoadMoreListener) {
        RecyclerManager recyclerManager = new RecyclerManager(recyclerView);
        recyclerManager.setLayoutManager(layoutManager);
        recyclerManager.setAdapter(adapter);
        recyclerManager.setOnItemClickListener(onItemClickListener);
        recyclerManager.setOnLoadMoreListener(onLoadMoreListener);
        return recyclerManager;
    }
}
