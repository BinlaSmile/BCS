package com.binla.bcs.model.project.request;

public class GetProjectListReqModel {
    //项目名(模糊搜索)
    private String projectName;
    //状态(已完成/进行中/未开始)
    private String status;
    //优先级
    private String priority;
    //里程碑
    private String milestone;
    //属主
    private String owners;
    //是否包含删除
    private boolean isDelete;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public String getOwners() {
        return owners;
    }

    public void setOwners(String owners) {
        this.owners = owners;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
