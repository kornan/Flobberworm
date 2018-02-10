package com.flobberworm.ui;

import com.flobberworm.framework.module.Page;

/**
 * PageUtil
 * Created by Kornan on 2018/1/30.
 */

public final class PageUtil {

    public static void updatePage(Page page, int total) {
        updateNextPage(page);
        updateTotalPage(page, total);
    }


    public static void updateNextPage(Page page) {
        page.setCurrentPage(page.getNextPage());
        page.setNextPage(page.getNextPage() + 1);
    }

    public static void updateTotalPage(Page page, int total) {
        page.setTotalCount(total);
        page.setCountPage((int) Math.ceil((float) page.getTotalCount() / (float) page.getPerPage()));
    }
}
