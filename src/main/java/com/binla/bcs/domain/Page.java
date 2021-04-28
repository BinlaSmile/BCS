package com.binla.bcs.domain;

import java.io.Serializable;
import java.util.List;

public class Page <T> implements Serializable {

    private int startIndex;//起始页

    private int pageSize;//单页条数

    private int pageNumber;//当前页

    private List<T> list;//数据

    private int totalRecord;//总条目

    private int totalPage;//总页数

    public Page() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getStartIndex() {
        return startIndex = (this.getPageNumber()-1)*this.getPageSize();
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }


    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }



    public int getTotalPage() {
        return totalPage = (this.getTotalRecord()%this.getPageSize()==0?(this.getTotalRecord()/this.getPageSize()):(this.getTotalRecord()/this.getPageSize()+1));
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }




}
