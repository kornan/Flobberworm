package com.flobberworm.framework.module;

/**
 * BaseView
 * Created by Kornan on 2017/11/15.
 */

public interface BaseView {

    void onNetworkError();

    void onTimeout();

    void onUnknownError(int code, String error);

    void onFailure(int code, String error);

    void onEnd();

}
