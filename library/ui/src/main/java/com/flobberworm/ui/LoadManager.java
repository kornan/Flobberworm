package com.flobberworm.ui;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.flobberworm.framework.views.layout.LoadingAndRetryManager;
import com.flobberworm.framework.views.layout.OnLoadingAndRetryListener;

/**
 * LoadManager
 * Created by Kornan on 2018/3/1.
 */

public class LoadManager {
    private OnLoadListener onLoadListener;

    public LoadManager() {
        this.onLoadListener = onLoadListener;
    }

    public LoadManager(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }

    public OnLoadListener getOnLoadListener() {
        return onLoadListener;
    }

    public LoadingAndRetryManager getmLoadingAndRetryManager() {
        return mLoadingAndRetryManager;
    }

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }

    private LoadingAndRetryManager mLoadingAndRetryManager;

    public void initLoadingAndRetryManager(final View view, final String title) {
        if (mLoadingAndRetryManager != null) {
            return;
        }
        mLoadingAndRetryManager = LoadingAndRetryManager.generate(view, new OnLoadingAndRetryListener() {
            @Override
            public void setRetryEvent(final View retryView) {
                TextView tvMessage = retryView.findViewById(R.id.tv_message);
                tvMessage.setText(title);
                retryView.findViewById(R.id.lay_root).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mLoadingAndRetryManager.showLoading();
                        if (onLoadListener != null) {
                            onLoadListener.onLoadingClick(retryView);
                        }
                    }
                });
            }

            @Override
            public void setEmptyEvent(final View emptyView) {
                super.setEmptyEvent(emptyView);
                TextView tvMessage = emptyView.findViewById(R.id.textView);
                tvMessage.setText(title);
                emptyView.findViewById(R.id.lay_root).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mLoadingAndRetryManager.showLoading();
                        if (onLoadListener != null) {
                            onLoadListener.onLoadingClick(emptyView);
                        }
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
            public void setRetryEvent(final View retryView) {
                TextView tvMessage = retryView.findViewById(R.id.tv_message);
                tvMessage.setText(title);
                retryView.findViewById(R.id.lay_root).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mLoadingAndRetryManager.showLoading();
                        if (onLoadListener != null) {
                            onLoadListener.onLoadingClick(retryView);
                        }
                    }
                });
            }

            @Override
            public void setEmptyEvent(final View emptyView) {
                super.setEmptyEvent(emptyView);
                TextView tvMessage = emptyView.findViewById(R.id.textView);
                tvMessage.setText(title);
                emptyView.findViewById(R.id.lay_root).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mLoadingAndRetryManager.showLoading();
                        if (onLoadListener != null) {
                            onLoadListener.onLoadingClick(emptyView);
                        }
                    }
                });
            }
        });
    }

    public void initLoadingAndRetryManager(final Activity activity, final String title) {
        if (mLoadingAndRetryManager != null) {
            return;
        }
        mLoadingAndRetryManager = LoadingAndRetryManager.generate(activity, new OnLoadingAndRetryListener() {
            @Override
            public void setRetryEvent(final View retryView) {
                TextView tvMessage = retryView.findViewById(R.id.tv_message);
                tvMessage.setText(title);
                retryView.findViewById(R.id.lay_root).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mLoadingAndRetryManager.showLoading();
                        if (onLoadListener != null) {
                            onLoadListener.onLoadingClick(retryView);
                        }
                    }
                });
            }

            @Override
            public void setEmptyEvent(final View emptyView) {
                super.setEmptyEvent(emptyView);
                TextView tvMessage = emptyView.findViewById(R.id.textView);
                tvMessage.setText(title);
                emptyView.findViewById(R.id.lay_root).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mLoadingAndRetryManager.showLoading();
                        if (onLoadListener != null) {
                            onLoadListener.onLoadingClick(emptyView);
                        }
                    }
                });
            }
        });
    }

    public interface OnLoadListener {
        void onLoadingClick(View view);
    }
}
