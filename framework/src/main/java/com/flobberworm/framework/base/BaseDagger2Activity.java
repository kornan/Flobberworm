package com.flobberworm.framework.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.flobberworm.framework.R;
import com.flobberworm.framework.utils.SnackBarHelper;

import dagger.android.support.DaggerAppCompatActivity;


/**
 * Base Dagger2 Activity
 * Created by Kornan on 2017/11/17.
 */

public abstract class BaseDagger2Activity extends DaggerAppCompatActivity {
    protected Toolbar toolbar;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initDefaultToolbar(Toolbar toolbar, boolean showBack) {
        this.toolbar = toolbar;
        if (showBack) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        } else {
            toolbar.setNavigationIcon(null);
        }
    }

    @Deprecated
    public void showProgress() {
//        ProgressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Deprecated
    public void stopProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void showLoading() {

    }

    public void hideLoading() {

    }

    public void onNetworkError() {
        View view = getRootView();
        if (view != null) {
            SnackBarHelper.getInstance().shown(view, R.string.fw_no_internet, Snackbar.LENGTH_LONG);
        }
    }

    public void onTimeout() {
        View view = getRootView();
        if (view != null) {
            SnackBarHelper.getInstance().shown(view, R.string.fw_net_timeout, Snackbar.LENGTH_LONG);
        }
    }

    public void onUnknownError(int code, String error) {
        View view = getRootView();
        if (view != null) {
            SnackBarHelper.getInstance().shown(view, error, Snackbar.LENGTH_LONG);
        }
    }

    public void onFailure(int code, String error) {
        View view = getRootView();
        if (view != null) {
            SnackBarHelper.getInstance().shown(view, error, Snackbar.LENGTH_LONG);
        }
    }

    public View getRootView() {
        return ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
    }
}
