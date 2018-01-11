package com.flobberworm.framework.module;

/**
 * Page
 * Created by Kornan on 2017/11/22.
 */

public class Page {


    /**
     * Current page
     */
    private int currentPage = 0;
    /**
     * Total pages
     */
    private int countPage = 1;
    /**
     * Per page max number
     */
    private int perPage = 10;
    /**
     * The total number
     */
    private int totalCount = 0;
    /**
     * next page
     */
    private int nextPage = 1;

    public Page() {
    }

    /**
     * reset default
     */
    public void reset() {
        currentPage = 0;
        countPage = 1;
        perPage = 10;
        totalCount = 0;
        nextPage = 1;
    }

    /**
     * Is the last page
     *
     * @return boolean
     */
    public boolean isLastPage() {
        return currentPage >= countPage;
    }

    public boolean isFirstPage() {
        return currentPage <= 1;
    }

    public int getCurrentPage() {
        if (currentPage < 1) {
            currentPage = 1;
        }
        return currentPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCountPage() {
        return countPage;
    }

    public void setCountPage(int countPage) {
        this.countPage = countPage;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }


}
