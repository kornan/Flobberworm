package com.flobberworm.framework.api;

import android.content.Context;

import com.flobberworm.framework.BaseApplication;

import org.reactivestreams.Subscriber;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.flobberworm.framework.api.NetConfig.CACHE_NAME;


/**
 * ApiManager API管理类
 * Created by kornan on 2017/8/27.
 */
public class APIManager {
    private static APIManager instance = null;
    private Retrofit retrofit;
    private Cache cache;

    public static APIManager getInstance() {
        return getInstance(BaseApplication.getInstance(), true);
    }

    public static APIManager getInstance(Context context) {
        return getInstance(context, true);
    }

    public static APIManager getInstance(Context context, boolean isRxJava) {
        if (null == instance) {
            synchronized (APIManager.class) {
                if (null == instance) {
                    instance = new APIManager(context, isRxJava);
                }
            }
        }
        return instance;
    }

    private APIManager(Context context, boolean isRxJava) {
//        this.context = context;
        File httpCacheDirectory = new File(context.getCacheDir(), CACHE_NAME);
        cache = new Cache(httpCacheDirectory, NetConfig.CACHE_SIZE);
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(NetConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())//Gson解析
                .client(getOkHttpClient());
        if (isRxJava) {
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        }
        this.retrofit = builder.build();
    }

    /**
     * 设置OkHttpClient请求
     */
    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                .addNetworkInterceptor(new HttpCacheInterceptor())
                .connectTimeout(NetConfig.HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(NetConfig.HTTP_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(NetConfig.HTTP_READ_TIMEOUT, TimeUnit.SECONDS)
                .cache(cache);
        return builder.build();
    }

    public <T> T createApi(Class<T> mClass) {
        return retrofit.create(mClass);
    }

    public static <T> Subscriber<T> setSubscribe(Flowable<T> observable, Subscriber<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(subscriber);
        return subscriber;
    }
}
