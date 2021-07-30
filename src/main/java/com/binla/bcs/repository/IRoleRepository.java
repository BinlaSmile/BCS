package com.binla.bcs.repository;

import com.binla.bcs.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleRepository {

    Role getById(int id);
    List<Role> getAll();
    List<Role> getByIds(@Param("ids")List<Integer> ids);
}
