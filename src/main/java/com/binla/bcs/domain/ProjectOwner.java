package com.binla.bcs.domain;

import com.binla.bcs.domain.common.BaseEntity;

public class ProjectOwner extends BaseEntity {
    private int id;
    private String userCode;
    private String projectNo;
    private String weight;
    private int progress;

    public ProjectOwner(int id, String userCode, String projectNo, String weight, int progress) {
        this.id = id;
        this.userCode = userCode;
        this.projectNo = projectNo;
        this.weight = weight;
        this.progress = progress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
