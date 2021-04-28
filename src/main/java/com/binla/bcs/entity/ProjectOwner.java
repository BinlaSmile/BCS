package com.binla.bcs.entity;

import com.binla.bcs.entity.common.BaseEntity;
import lombok.Data;

@Data
public class ProjectOwner extends BaseEntity {
    private int id;
    private String userCode;
    private String projectNo;
    private String weight;
    private int progress;
}
