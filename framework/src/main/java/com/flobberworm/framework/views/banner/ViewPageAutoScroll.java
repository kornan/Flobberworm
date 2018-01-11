package com.flobberworm.framework.views.banner;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Introduction:
 * Created by KORNAN on 2016/9/20.
 */
public class ViewPageAutoScroll extends ViewPager {
    private ViewGroup parent;
    private int mScrollTime = 0;
    private Timer timer;
    private Activity mActivity;

    public ViewPageAutoScroll(Context context) {
        super(context);
    }

    public ViewPageAutoScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void start(Activity mActivity, int mScrollTime) {
        this.mActivity = mActivity;
        this.mScrollTime = mScrollTime;
        if (mScrollTime != 0 && getAdapter().getCount() > 1) {
            startTimer();

            this.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        startTimer();
                    } else {
                        stopTimer();
                    }
                    return false;
                }
            });
        }
    }

    /**
     * 开始计时
     */
    public void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                mActivity.runOnUiThread(new Runnable() {
                    public void run() {
                        int position = ViewPageAutoScroll.this.getCurrentItem() + 1;
                        if (position >= getAdapter().getCount()) {
                            position = 0;
                        }
                        ViewPageAutoScroll.this.setCurrentItem(position);//设置控件当前项（改变图片）
                    }
                });
            }
        }, mScrollTime, mScrollTime);
    }

    /**
     * 停止计时
     */
    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

//
//    public void setNestedpParent(ViewGroup parent) {
//        this.parent = parent;
//    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (parent != null) {
//            parent.requestDisallowInterceptTouchEvent(true);
//        }
//        return super.dispatchTouchEvent(ev);
//    }
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent arg0) {
//        if (parent != null) {
//            parent.requestDisallowInterceptTouchEvent(true);
//        }
//        return super.onInterceptTouchEvent(arg0);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent arg0) {
//        if (parent != null) {
//            parent.requestDisallowInterceptTouchEvent(true);
//        }
//        return super.onTouchEvent(arg0);
//    }
}
