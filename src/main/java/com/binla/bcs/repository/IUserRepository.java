package com.binla.bcs.repository;

import com.binla.bcs.domain.QueryCondition;
import com.binla.bcs.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository {
    User getByCode(String code);
    User getByName(String name);
    User getByCodePassword(String code,String password);
    User getByNamePassword(String name,String password);
    List<User> getAll();
    List<User> getList(String condition, Integer role);
    List<User> getListByCodes(@Param("codes")List<String> codes);
    List<User> getListByRoles(@Param("roles")List<Integer> roles);
    int getPageCount(String condition, Integer role);
    List<User> getPageList(String condition, Integer role, @Param("query") QueryCondition query);

    void insert(@Param("user")User user);
    void update(@Param("user")User user);
    void deleteByCode(String code);
}
