package com.binla.bcs.entity;

import com.binla.bcs.entity.common.BaseEntity;
import lombok.Data;

@Data
public class Permission extends BaseEntity {
    private String code;
    private String desc;
}
