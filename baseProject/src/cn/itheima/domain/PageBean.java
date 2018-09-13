package cn.itheima.domain;

import java.util.List;

/**
 * pageBean分页实体类
 **/
public class PageBean<T> {
    /**当前页码,由客户端传递*/
    private int currentPage;
    /**总记录数,查询数据库所得*/
    private  int totalRecode;
    /**每页记录数,由程序固定*/
    private int pageCount;
    /**当前页的记录*/
    private List<T> beanList;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**封装url*/
    private String url;
    public PageBean() {
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    /**总记页数,总页数=总记录数/每页记录数*/
    public int getTotalPage() {
        return (int)Math.ceil(totalRecode*1.0/pageCount);
    }

    public int getTotalRecode() {
        return totalRecode;
    }

    public void setTotalRecode(int totalRecode) {
        this.totalRecode = totalRecode;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<T> beanList) {
        this.beanList = beanList;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", totalRecode=" + totalRecode +
                ", pageCount=" + pageCount +
                ", beanList=" + beanList +
                '}';
    }
}
