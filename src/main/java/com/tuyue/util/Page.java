package com.tuyue.util;

/**
 * Created by Administrator on 2017/6/30.
 */

import java.util.List;

public class Page<T> {

    private int currentPage = 1;//当前页
    private int allpage = 0;//一共有几页
    private long total = 1;//总条数
    private int pageSize = 10;//每页显示几条
    private String orderBy = "";
    private String order = "";

    @Override
    public String toString() {
        return "Page{" + "currentPage=" + currentPage + ", allpage=" + allpage + ", total=" + total + ", pageSize=" + pageSize + ", orderBy='" + orderBy + '\'' + ", order='" + order + '\'' + ", list=" + list + '}';
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    private List<T> list = null;

    public Page() {

    }

    public Page(int currentPage, int pageSize) {
        currentPage = currentPage;
        pageSize = pageSize;
    }

    public Page(List<T> list, int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.list = list;
    }

    public Page(List<T> list, long total, int currentPage, int pageSize, String orderBy, String order) {
        this.list = list;
        this.currentPage = currentPage;
        this.total = total;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.order = order;
        this.allpage = total == 0 ? 1 : (int) ((total - 1) / pageSize + 1);//分几页的计算方法
//      this.allpage  = total == 0 ? 1 : (int)  ((total - 1) / pageSize + 1);//分几页的计算方法

        if (this.currentPage > this.allpage) {
            this.currentPage = this.allpage;
        }
        if (currentPage < 1) {
            this.currentPage = 1;
        }
    }

    public Page(List<T> list, long records, int current, int pageSize) {
        this(list, records, current, pageSize, null, null);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getAllpage() {
        return allpage;
    }

    public void setallPage(int l) {
        this.allpage = l;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


}
