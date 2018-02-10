package com.flobberworm.framework.base;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.flobberworm.framework.R;
import com.flobberworm.framework.utils.SnackBarHelper;


/**
 * BaseFragment
 * Created by Kornan on 2017/11/29.
 */

public class BaseFragment extends Fragment {

    protected void initToolbar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    public void showLoading() {

    }

    public void hideLoading() {

    }

    public void onNetworkError() {
        View view = getView();
        if (view != null) {
            SnackBarHelper.getInstance().shown(view, R.string.fw_no_internet, Snackbar.LENGTH_LONG);
        }
    }

    public void onTimeout() {
        View view = getView();
        if (view != null) {
//            SnackBarHelper.show(view, "" + getString(R.string.net_timeout), Snackbar.LENGTH_LONG);
            SnackBarHelper.getInstance().shown(view, R.string.fw_net_timeout, Snackbar.LENGTH_LONG);
        }
    }

    public void onUnknownError(int code, String error) {
        View view = getView();
        if (view != null) {
//            SnackBarHelper.show(view, error, Snackbar.LENGTH_LONG);
            SnackBarHelper.getInstance().shown(view, error, Snackbar.LENGTH_LONG);
        }
    }

    public void onFailure(int code, String error) {
        View view = getView();
        if (view != null) {
            SnackBarHelper.getInstance().shown(view, error, Snackbar.LENGTH_LONG);
        }
    }
}
