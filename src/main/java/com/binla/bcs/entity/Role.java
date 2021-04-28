package com.binla.bcs.entity;

import com.binla.bcs.entity.common.BaseEntity;
import lombok.Data;

@Data
public class Role extends BaseEntity {
    private int id;
    private String name;

}
