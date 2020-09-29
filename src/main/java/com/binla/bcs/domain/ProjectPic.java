package com.binla.bcs.domain;

import com.binla.bcs.domain.common.BaseEntity;

public class ProjectPic extends BaseEntity {
    private int id;
    private String projectNo;
    private String pic;

    public ProjectPic(int id, String projectNo, String pic) {
        this.id = id;
        this.projectNo = projectNo;
        this.pic = pic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
