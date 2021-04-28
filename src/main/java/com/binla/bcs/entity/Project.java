package com.binla.bcs.entity;

import com.binla.bcs.entity.common.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class Project extends BaseEntity {
    private String projectNo;
    private String name;
    private Date beginDate;
    private Date completionDate;
    private int priority;
    private String milestone;
    private String des;
    private String addition;
}
