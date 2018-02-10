package com.flobberworm.framework.base;


import com.flobberworm.framework.module.BaseView;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;

import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * CallbackWrapper
 * Created by Kornan on 2017/11/20.
 */

public abstract class CallbackWrapper<T> extends DisposableSubscriber<Response<T>> {
    private WeakReference<BaseView> weakReference;

    public CallbackWrapper(BaseView view) {
        this.weakReference = new WeakReference<>(view);
    }

    protected void onSuccessHeader(Headers headers) {

    }

    protected abstract void onSuccess(T t);

    protected void onFailure(int code, String message) {
        BaseView view = weakReference.get();
        if (view != null) {
            weakReference.get().onFailure(code, message);
        }
    }

    @Override
    public void onNext(Response<T> tResponse) {
        if (tResponse.isSuccessful()) {
            onSuccessHeader(tResponse.headers());
            onSuccess(tResponse.body());
        } else {
            String message = getErrorMessage(tResponse.errorBody());
            onFailure(tResponse.code(), message);
//            onFailure(tResponse.code(), tResponse.message());
        }
    }

    @Override
    public void onComplete() {
        BaseView baseView = weakReference.get();
        if (baseView != null) {
            baseView.onEnd();
        }
    }

    public String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public void onError(Throwable t) {
        BaseView view = weakReference.get();
        if (view == null) {
            return;
        }
        if (t instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) t).response().errorBody();
            view.onUnknownError(((HttpException) t).code(), getErrorMessage(responseBody));
        } else if (t instanceof SocketTimeoutException) {
            view.onTimeout();
        } else if (t instanceof IOException) {
            view.onNetworkError();
        } else {
            view.onUnknownError(t.hashCode(), t.getMessage());
        }
        view.onEnd();
    }
}
