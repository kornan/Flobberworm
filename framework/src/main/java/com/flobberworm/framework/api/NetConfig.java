package com.flobberworm.framework.api;


/**
 * Net config manager
 * Created by kornan on 2017/9/5.
 */

public interface NetConfig {
    public static final String API_URL = "https://wxopen.szxnm.com/wxopen/";
    public static final int HTTP_CONNECT_TIMEOUT = 10 * 1000;
    public static final int HTTP_WRITE_TIMEOUT = 10 * 1000;
    public static final int HTTP_READ_TIMEOUT = 30 * 1000;
    public static final int CACHE_SIZE = 100 * 1024 * 1024; // 100 MiB
    public static final String CACHE_NAME = "responses";

}
