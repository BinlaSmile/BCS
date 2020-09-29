package com.binla.bcs.domain;

import com.binla.bcs.domain.common.BaseEntity;

public class Milestone extends BaseEntity {
    private String milestoneNo;
    private String des;

    public Milestone(String milestoneNo, String des) {
        this.milestoneNo = milestoneNo;
        this.des = des;
    }

    public String getMilestoneNo() {
        return milestoneNo;
    }

    public void setMilestoneNo(String milestoneNo) {
        this.milestoneNo = milestoneNo;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
