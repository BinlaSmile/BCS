package com.binla.bcs.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.binla.bcs.domain.constants.CacheConstant;
import com.binla.bcs.entity.Permission;
import com.binla.bcs.model.permission.PermissionInfoModel;
import com.binla.bcs.repository.IPermissionRepository;
import com.binla.bcs.service.IPermissionService;
import com.binla.bcs.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionRepository permissionRepository;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<PermissionInfoModel> getPermissionInfoByRole(int roleId,boolean allowCache) {
        List<PermissionInfoModel> result = new ArrayList<>();
        List<Permission> permissions = new ArrayList<>();
        if(allowCache){
            permissions = (List<Permission>) redisUtil.get(CacheConstant.ROLE_PERMISSION+roleId);
        }

        if(permissions == null || permissions.size() == 0)
            permissions = permissionRepository.getByRoleId(roleId);

        if(allowCache)
            redisUtil.set(CacheConstant.ROLE_PERMISSION+roleId,permissions,600);

        for(Permission item : permissions){
            result.add(new PermissionInfoModel(roleId,item.getCode(),item.getDesc()));
        }

        return result;
    }

}
