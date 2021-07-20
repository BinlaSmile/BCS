package com.binla.bcs.model.permission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionInfoModel {
    private int roleId;
    private String permissionCode;
    private String permissionDesc;
}
