package com.binla.bcs.entity;

import com.binla.bcs.entity.common.BaseEntity;
import lombok.Data;

@Data
public class User extends BaseEntity {
    private String code;
    private String name;
    private String password;
    private int role;
    private String salt;
    private String pic;
    private String color;
}
