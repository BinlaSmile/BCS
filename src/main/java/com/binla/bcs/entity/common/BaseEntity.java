package com.binla.bcs.entity.common;

import lombok.Data;

import java.util.Date;
@Data
public class BaseEntity {
    private String insertUser;
    private Date insertDate;
    private String updateUser;
    private Date updateDate;
}
