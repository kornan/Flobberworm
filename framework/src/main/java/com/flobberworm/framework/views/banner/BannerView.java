package com.flobberworm.framework.views.banner;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.ButtonBarLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.flobberworm.framework.R;

import java.util.List;


/**
 * Introduction:
 * Created by KORNAN on 2016/9/20.
 */
public class BannerView extends RelativeLayout {
    private final static int DEFAULT_SCROLL_TIME = 3000;
    private ViewPageAutoScroll viewPageAutoScroll;
    private ButtonBarLayout buttonPanel;
    private BannerAdapter bannerAdapter;


    public BannerView(Context context) {
        super(context);
        init(context);
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.fw_layout_auto_scroll, this);
        buttonPanel = findViewById(R.id.buttonPanel);
        viewPageAutoScroll = findViewById(R.id.viewPageAutoScroll);
        bannerAdapter = new BannerAdapter(context);
        viewPageAutoScroll.addOnPageChangeListener(new OnBannerPageChangeListener());
        viewPageAutoScroll.setAdapter(bannerAdapter);
    }

    /**
     * 添加滚动监听
     *
     * @param onPageChangeListener 事件
     */
    public void addOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        viewPageAutoScroll.addOnPageChangeListener(onPageChangeListener);
    }

    public void addDatas(List<String> bannerList) {
        bannerAdapter.getBannerList().addAll(bannerList);
        if (bannerList.size() > 1) {
            for (int i = 0; i < bannerList.size(); i++) {
                if (i == 0) {
                    addDotView(true);
                } else {
                    addDotView(false);
                }
            }
        }
    }

    public void setCurrentItem(int position) {
        viewPageAutoScroll.setCurrentItem(position);
    }

    public List<String> getBannerList() {
        return bannerAdapter.getBannerList();
    }

    /**
     * 添加圆点
     */
    private void addDotView(boolean selected) {
        AppCompatImageView btn = new AppCompatImageView(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(8, 0, 0, 0);
        btn.setLayoutParams(lp);
        btn.setBackgroundResource(R.drawable.fw_dot_normal);
        if (selected) btn.setSelected(true);
        buttonPanel.addView(btn);
    }

    /**
     * 开始滚动
     *
     * @param mActivity
     * @param mScrollTime
     */
    public void start(Activity mActivity, int mScrollTime) {
        viewPageAutoScroll.start(mActivity, mScrollTime);
    }

    /**
     * 开始滚动
     *
     * @param mActivity
     */
    public void start(Activity mActivity) {
        viewPageAutoScroll.start(mActivity, DEFAULT_SCROLL_TIME);
    }

    /**
     * 停止滚动
     */
    public void stop() {
        viewPageAutoScroll.stopTimer();
    }


    public void clear() {
        bannerAdapter.getBannerList().clear();
        buttonPanel.removeAllViews();
    }

    public void notifyDataSetChanged() {
        bannerAdapter.notifyDataSetChanged();
        if (bannerAdapter.getCount() > 0) {
            viewPageAutoScroll.setCurrentItem(bannerAdapter.getItemPosition(0));
        }
    }

    public void setOnBannerListener(BannerView.OnBannerListener onBannerListener) {
        this.bannerAdapter.setOnBannerListener(onBannerListener);
    }


    class OnBannerPageChangeListener implements ViewPager.OnPageChangeListener {
        private boolean isInit = true;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (isInit) {
                isInit = false;
                setChildSelect(position % bannerAdapter.getBannerList().size());
            }
        }

        @Override
        public void onPageSelected(int position) {
            setChildSelect(position % bannerAdapter.getBannerList().size());
        }

        private void setChildSelect(int position) {
            for (int i = 0; i < buttonPanel.getChildCount(); i++) {
                if (i == position) {
                    buttonPanel.getChildAt(position).setSelected(true);
                } else
                    buttonPanel.getChildAt(i).setSelected(false);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state != ViewPager.SCROLL_STATE_IDLE) {
                bannerAdapter.getOnBannerListener().onBannerItemTouch();
            } else {
                bannerAdapter.getOnBannerListener().onBannerItemTouchUp();
            }
        }
    }

    public interface OnBannerListener {
        void onBannerItemTouch();

        void onBannerItemTouchUp();

        void onBannerItemClick(View view, int position);

        void onBannerItemLongClick(View view, int position);
    }

}
