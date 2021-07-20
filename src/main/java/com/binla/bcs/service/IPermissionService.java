package com.binla.bcs.service;

import com.binla.bcs.model.permission.PermissionInfoModel;

import java.util.List;

public interface IPermissionService {

    List<PermissionInfoModel> getPermissionInfoByRole(int roleId,boolean allowCache);

}
