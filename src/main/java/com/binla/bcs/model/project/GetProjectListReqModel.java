package com.binla.bcs.model.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GetProjectListReqModel {
    //项目名(模糊搜索)
    private String projectName;
    //状态(已完成/进行中/未开始)
    private int status;
    //优先级
    private String priority;
    //里程碑
    private String milestone;
    //属主
    private String owners;
    //是否包含删除
    private boolean isDelete;

}
