package com.binla.bcs.entity;

import com.binla.bcs.entity.common.BaseEntity;
import lombok.Data;

@Data
public class RolePermission extends BaseEntity {
    private int roleId;
    private String permissionCode;
}
