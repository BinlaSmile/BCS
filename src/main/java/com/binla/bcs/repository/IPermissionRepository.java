package com.binla.bcs.repository;

import com.binla.bcs.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermissionRepository {

    List<Permission> getByRoleId(int roleId);
}
