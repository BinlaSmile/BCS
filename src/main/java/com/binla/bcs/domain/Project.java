package com.binla.bcs.domain;

import com.binla.bcs.domain.common.BaseEntity;

import java.util.Date;

public class Project extends BaseEntity {
    private String projectNo;
    private String name;
    private Date beginDate;
    private Date completionDate;
    private int priority;
    private String milestone;
    private String des;
    private String addition;

    public Project()
    {


    }
    public Project(String projectNo, String name, Date beginDate, Date completionDate, int priority, String milestone, String des, String addition) {
        this.projectNo = projectNo;
        this.name = name;
        this.beginDate = beginDate;
        this.completionDate = completionDate;
        this.priority = priority;
        this.milestone = milestone;
        this.des = des;
        this.addition = addition;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }
}
