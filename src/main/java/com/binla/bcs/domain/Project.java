package com.binla.bcs.domain;

import com.binla.bcs.domain.common.BaseEntity;

import java.util.Date;

public class Project extends BaseEntity {
    private int pid;
    private String title;
    private String desc;
    private Date beginDate;
    private Date endDate;
    private int schedule;
    public Project(){

    }
    public Project(int pid, String title, String desc, Date beginDate, Date endDate, int schedule) {
        this.pid = pid;
        this.title = title;
        this.desc = desc;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.schedule = schedule;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }
}
