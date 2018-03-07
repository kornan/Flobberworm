package com.flobberworm.ui;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.flobberworm.framework.base.BaseDagger2Fragment;
import com.flobberworm.framework.module.BaseAdapter;
import com.flobberworm.framework.module.Page;
import com.flobberworm.framework.views.layout.LoadingAndRetryManager;
import com.flobberworm.framework.views.layout.OnLoadingAndRetryListener;
import com.flobberworm.load.ItemClickSupport;
import com.flobberworm.load.OnLoadMoreListener;
import com.flobberworm.load.RecyclerManager;

/**
 * BaseRecycleFragment
 * Created by Kornan on 2018/1/30.
 */

public abstract class SimpleRecycleFragment extends BaseDagger2Fragment
        implements SwipeRefreshLayout.OnRefreshListener, ItemClickSupport.OnItemClickListener, OnLoadMoreListener, LoadManager.OnLoadListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private BaseAdapter adapter;
    protected Page page;
    protected RecyclerManager recyclerManager;

    protected LoadingAndRetryManager mLoadingAndRetryManager;
    protected LoadManager loadManager;

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        if (position < adapter.getItemCount()) {
            onItemClick(recyclerView, position, v);
        }
    }

    public void onEnd() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }

        // TODO: 2018/2/7
        try {
            if (adapter.getItemCount() > 0) {
                mLoadingAndRetryManager.showContent();
            } else {
                mLoadingAndRetryManager.showEmpty();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public void onLoadMore() {
        if (recyclerManager.isLoading()) {
            request();
        }
    }

    public void initLoadManager() {
        loadManager = new LoadManager(this);
    }

    public void initSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
//        ViewUtils.setColorScheme(swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    public void initRecyclerManager(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager) {
        adapter = getBaseAdapter();
        recyclerManager = ViewUtils.setRecyclerManager(recyclerView, adapter, layoutManager, this, this);
    }

    public void initRecyclerManager(RecyclerView recyclerView,
                                    BaseAdapter adapter,
                                    RecyclerView.LayoutManager layoutManager,
                                    ItemClickSupport.OnItemClickListener onItemClickListener,
                                    OnLoadMoreListener onLoadMoreListener) {
        this.adapter = adapter;
        recyclerManager = ViewUtils.setRecyclerManager(recyclerView, adapter, layoutManager, onItemClickListener, onLoadMoreListener);
    }

    public abstract void onItemClick(RecyclerView recyclerView, int position, View v);

    public abstract BaseAdapter getBaseAdapter();

    public abstract void request();


    @Override
    public void onFailure(int code, String error) {
        super.onFailure(code, error);
        loadManager.getmLoadingAndRetryManager().showRetry();
    }

    @Override
    public void onNetworkError() {
        super.onNetworkError();
        loadManager.getmLoadingAndRetryManager().showRetry();
    }

    @Override
    public void onTimeout() {
        super.onTimeout();
        loadManager.getmLoadingAndRetryManager().showRetry();
    }

    @Override
    public void onUnknownError(int code, String error) {
        super.onUnknownError(code, error);
        loadManager.getmLoadingAndRetryManager().showRetry();
    }

}
