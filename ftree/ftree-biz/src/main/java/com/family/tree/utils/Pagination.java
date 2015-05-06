package com.family.tree.utils;

/**
 *
 * @author lengda
 * @since 2012-7-30
 */
public class Pagination {
    private static final int DEFAULT_PAGE_SIZE = 10;

    private int pageSize = DEFAULT_PAGE_SIZE; // 每页的记录数
    private int pageStart = 0; // 当前记录数, base 0
    private int pageIndex = 1; // 当前第几页, base 1
    private int pageTotal = 0; // 总页数
    private int total = 0; // 总记录数

    public Pagination() {
    }

    public Pagination(int total, int pageSize) {
        this.pageSize = pageSize;
        setTotal(total);
    }

    public Pagination(int total, int pageIndex, int pageSize) {
        setPageIndex(pageIndex);
        setPageSize(pageSize);
        setTotal(total);
    }

    public void nextPage() {
        setPageIndex(getNextPage());
    }

    public void setPageIndex(int pageIndex) {
        if (pageIndex > 0) {
            pageStart = (pageIndex - 1) * pageSize;
            this.pageIndex = pageIndex;
        } else {
            pageIndex = 1;
        }
    }

    public int getPageIndex() {
        return pageIndex > 0 ? pageIndex : 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageStart() {
        return pageStart;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
        pageTotal = (int) Math.ceil((total + pageSize - 1) / pageSize);
        pageStart = (getPageIndex() - 1) * pageSize;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public int getNextPage() {
        if (hasNextPage()) {
            return pageIndex + 1;
        }
        return pageIndex;
    }

    public int getPreviousPage() {
        if (hasPreviousPage()) {
            return pageIndex - 1;
        }
        return pageIndex;
    }

    public boolean hasNextPage() {
        return pageIndex < pageTotal;
    }

    public boolean hasPreviousPage() {
        return pageIndex > 1;
    }

}
