package com.flobberworm.framework.utils;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.flobberworm.framework.R;


/**
 * SnackBarHelper:
 * Created by Kornan on 2017/1/25.
 */
public class SnackBarHelper {
    private static SnackBarHelper snackBarHelper;
    private Snackbar snackbar;

    private SnackBarHelper() {
    }

    public static SnackBarHelper getInstance() {
        if (snackBarHelper == null) {
            snackBarHelper = new SnackBarHelper();
        }
        return snackBarHelper;
    }

    public void shown(@NonNull View view, int id, int duration) {
        if (snackbar == null) {
            snackbar = Snackbar.make(view, id, duration);
            View snackBarView = snackbar.getView();
            ((TextView) snackBarView.findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);
            snackbar.addCallback(new Snackbar.Callback() {
                @Override
                public void onShown(Snackbar sb) {
                }

                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    snackbar = null;
                }
            });
            snackbar.show();
        } else {
            snackbar.setText(id);
            snackbar.show();
        }
    }

    public void shown(@NonNull View view, @NonNull CharSequence text, int duration) {
        if (snackbar == null) {
            snackbar = Snackbar.make(view, text, duration);
            View snackBarView = snackbar.getView();
            ((TextView) snackBarView.findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);
            snackbar.addCallback(new Snackbar.Callback() {
                @Override
                public void onShown(Snackbar sb) {
                }

                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    snackbar = null;
                }
            });
            snackbar.show();
        } else {
            snackbar.setText(text);
            snackbar.show();
        }
    }

    @Deprecated
    public static void show(@NonNull View view, @NonNull CharSequence text, int duration) {
        Snackbar snackbar = Snackbar.make(view, text, duration);
        View snackBarView = snackbar.getView();
        ((TextView) snackBarView.findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);
        snackbar.show();
    }
}
