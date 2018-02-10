package com.flobberworm.framework;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;


/**
 * flobberworm team
 * Created by kornan on 2017/12/30.
 */

public class BaseApplication extends Application implements HasSupportFragmentInjector, HasActivityInjector {
    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        DaggerAppComponent.builder().application(this).build().inject(this);
        instance = this;
    }

    public void initConfig() {
    }

}
