package com.flobberworm.ui;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.flobberworm.framework.base.BaseDagger2Fragment;
import com.flobberworm.framework.base.BaseFragment;
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

public abstract class SimpleRecycleFragment extends BaseFragment
        implements SwipeRefreshLayout.OnRefreshListener, ItemClickSupport.OnItemClickListener, OnLoadMoreListener {
    //    protected RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private BaseAdapter adapter;
    //    protected BaseAdapter adapter;
    protected Page page;
    protected RecyclerManager recyclerManager;

    protected LoadingAndRetryManager mLoadingAndRetryManager;

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

    public void initLoadingAndRetryManager(final View view, final String title) {
        if (mLoadingAndRetryManager != null) {
            return;
        }
        mLoadingAndRetryManager = LoadingAndRetryManager.generate(view, new OnLoadingAndRetryListener() {
            @Override
            public void setRetryEvent(View retryView) {
                TextView tvMessage = retryView.findViewById(R.id.tv_message);
                tvMessage.setText(title);
                retryView.findViewById(R.id.lay_root).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mLoadingAndRetryManager.showLoading();
                    }
                });
            }

            @Override
            public void setEmptyEvent(View emptyView) {
                super.setEmptyEvent(emptyView);
                TextView tvMessage = emptyView.findViewById(R.id.textView);
                tvMessage.setText(title);
                emptyView.findViewById(R.id.lay_root).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mLoadingAndRetryManager.showLoading();
                    }
                });
            }
        });
    }

    public void initLoadingAndRetryManager(final Fragment fragment, final String title) {
        if (mLoadingAndRetryManager != null) {
            return;
        }
        mLoadingAndRetryManager = LoadingAndRetryManager.generate(fragment, new OnLoadingAndRetryListener() {
            @Override
            public void setRetryEvent(View retryView) {
                TextView tvMessage = retryView.findViewById(R.id.tv_message);
                tvMessage.setText(title);
                retryView.findViewById(R.id.lay_root).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mLoadingAndRetryManager.showLoading();
                    }
                });
            }

            @Override
            public void setEmptyEvent(View emptyView) {
                super.setEmptyEvent(emptyView);
                TextView tvMessage = emptyView.findViewById(R.id.textView);
                tvMessage.setText(title);
                emptyView.findViewById(R.id.lay_root).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mLoadingAndRetryManager.showLoading();
                    }
                });
            }
        });
    }

}
