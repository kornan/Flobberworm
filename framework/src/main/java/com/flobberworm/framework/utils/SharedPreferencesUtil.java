package com.flobberworm.framework.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences Util
 * Created by Kornan on 2017/12/22.
 */

public class SharedPreferencesUtil {
    private Context mContext;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mPreferences;
    private String mFileName = "";
    private int mMode = 0;
    private static final String TAG = SharedPreferencesUtil.class.getSimpleName();

    @SuppressLint("CommitPrefEdits")
    public SharedPreferencesUtil(Context context, String fileName) {
        this.mContext = context;
        this.mPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        this.mEditor = this.mPreferences.edit();
        mFileName = fileName;
        mMode = Context.MODE_PRIVATE;
    }

    @SuppressLint("CommitPrefEdits")
    public SharedPreferencesUtil(Context context, String fileName, int mode) {
        this.mContext = context;
        this.mPreferences = context.getSharedPreferences(fileName, mode);
        this.mEditor = this.mPreferences.edit();
        mFileName = fileName;
        mMode = mode;
    }

    public void apply() {
        this.mEditor.apply();
    }

    // 读写配置文件

    public boolean putString(String name, String value) {
        mEditor.putString(name, value);
        return mEditor.commit();
    }

    public boolean putLong(String name, Long value) {
        mEditor.putLong(name, value);
        return mEditor.commit();
    }

    public boolean putInt(String name, int value) {
        mEditor.putInt(name, value);
        return mEditor.commit();
    }

    public boolean putBoolean(String name, Boolean value) {
        mEditor.putBoolean(name, value);
        return mEditor.commit();
    }

    public boolean remove(String name) {
        mEditor.remove(name);
        return mEditor.commit();
    }

    public boolean clear() {
        mEditor.clear();
        return mEditor.commit();
    }

    public long getLong(String key) {
        return mPreferences.getLong(key, 0);
    }

    public int getInt(String key) {
        return mPreferences.getInt(key, 0);
    }

    public Boolean getBoolean(String key) {
        return mPreferences.getBoolean(key, false);
    }

    public String getString(String key) {
        return mPreferences.getString(key, "");
    }

    public long getLong(String key, long defValue) {
        return mPreferences.getLong(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return mPreferences.getInt(key, defValue);
    }

    public Boolean getBoolean(String key, boolean defValue) {
        return mPreferences.getBoolean(key, defValue);
    }

    public String getString(String key, String defValue) {
        return mPreferences.getString(key, defValue);
    }

    public SharedPreferences.Editor getEditor() {
        return mEditor;
    }
}
