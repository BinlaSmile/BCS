package com.binla.bcs.entity;

import com.binla.bcs.entity.common.BaseEntity;
import lombok.Data;

@Data
public class Item extends BaseEntity {
    private int id;
    private String code;
    private String name;
    private String pic;
    private int amount;
}
