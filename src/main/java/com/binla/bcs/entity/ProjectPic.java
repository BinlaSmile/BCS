package com.binla.bcs.entity;

import com.binla.bcs.entity.common.BaseEntity;
import lombok.Data;

@Data
public class ProjectPic extends BaseEntity {
    private int id;
    private String projectNo;
    private String pic;
}
