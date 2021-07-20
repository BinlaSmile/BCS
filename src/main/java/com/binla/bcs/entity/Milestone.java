package com.binla.bcs.entity;

import com.binla.bcs.entity.common.BaseEntity;
import lombok.Data;

@Data
public class Milestone extends BaseEntity {
    private String milestoneNo;
    private String desc;
}
