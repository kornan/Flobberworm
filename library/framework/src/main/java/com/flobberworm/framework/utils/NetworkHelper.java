/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.flobberworm.framework.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Generic reusable network methods.
 */
public class NetworkHelper {

    /**
     * Not connected to the internet
     */
    public static final int NETWORK_NONE = -1;
    /**
     * mobile network
     */
    public static final int NETWORK_MOBILE = 0;
    /**
     * WIFI network
     */
    public static final int NETWORK_WIFI = 1;

    /**
     * @param context to use to check for network connectivity.
     * @return true if connected, false otherwise.
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission")
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * Is it WIFI?
     *
     * @param context context
     * @return boolean
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission")
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        } else {
            return false;
        }
    }

    public static int getNetWorkState(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        @SuppressLint("MissingPermission")
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {

            if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_WIFI)) {
                return NETWORK_WIFI;
            } else if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_MOBILE)) {
                return NETWORK_MOBILE;
            }
        } else {
            return NETWORK_NONE;
        }
        return NETWORK_NONE;
    }

    /**
     * Get the type of network
     *
     * @param context context
     * @return type
     */
    public static int getNetWorkStatus(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission")
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo.getType();
    }

    /***
     *  Get the type of mobile network
     *  param context
     */
    private static boolean isFastMobileNetwork(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return false; // ~ 14-64 kbps
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return true; // ~ 400-1000 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return true; // ~ 600-1400 kbps
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return false; // ~ 100 kbps
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return true; // ~ 2-14 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return true; // ~ 700-1700 kbps
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return true; // ~ 1-23 Mbps
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return true; // ~ 400-7000 kbps
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return true; // ~ 1-2 Mbps
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return true; // ~ 5 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return true; // ~ 10-20 Mbps
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return false; // ~25 kbps
            case TelephonyManager.NETWORK_TYPE_LTE:
                return true; // ~ 10+ Mbps
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return false;
            default:
                return false;
        }
    }
}
