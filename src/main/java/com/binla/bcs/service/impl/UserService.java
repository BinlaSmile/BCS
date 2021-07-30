package com.binla.bcs.service.impl;

import com.binla.bcs.domain.Page;
import com.binla.bcs.domain.QueryCondition;
import com.binla.bcs.entity.Role;
import com.binla.bcs.entity.User;
import com.binla.bcs.entity.common.BaseEntity;
import com.binla.bcs.model.user.request.CreateUserReqModel;
import com.binla.bcs.model.user.request.GetPageUserReqModel;
import com.binla.bcs.model.user.request.GetUserReqModel;
import com.binla.bcs.model.user.response.UserDataModel;
import com.binla.bcs.model.user.response.UserInfoModel;
import com.binla.bcs.model.user.response.UserModel;
import com.binla.bcs.model.user.response.UserPageInfoModel;
import com.binla.bcs.repository.IRoleRepository;
import com.binla.bcs.repository.IUserRepository;
import com.binla.bcs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public UserDataModel getDataByCode(String code) {
        var entity = userRepository.getByCode(code);
        if(entity!=null){
            return new UserDataModel(entity.getCode(),entity.getName(),entity.getRole(),entity.getPic(),entity.getColor(),
                    entity.getPassword(),entity.getSalt(),entity.getInsertUser(),entity.getInsertDate(),
                    entity.getUpdateUser(),entity.getUpdateDate());
        }
        return null;
    }

    @Override
    public UserModel getByCode(String code) {
        var entity = userRepository.getByCode(code);
        if(entity!=null){
            return new UserModel(entity.getCode(),entity.getName(),entity.getRole(),entity.getPic(),entity.getColor());
        }
        return null;
    }

    @Override
    public UserInfoModel getInfoByCode(String code) {
        var entity = userRepository.getByCode(code);
        if(entity!=null){
            var role = roleRepository.getById(entity.getRole());
            var roleName = role==null?"":role.getName();
            return new UserInfoModel(entity.getCode(),entity.getName(),entity.getRole(),roleName,entity.getPic(),entity.getColor());
        }
        return null;
    }

    @Override
    public List<UserPageInfoModel> getList(GetUserReqModel getUserReq) {
        var result = new ArrayList<UserPageInfoModel>();
        var userList = userRepository.getList(getUserReq.getCondition(),getUserReq.getRole());
        if(userList!=null && userList.size()>0){
            var operatorCodeList = userList.stream().map(User::getInsertUser).collect(Collectors.toList());
            operatorCodeList.addAll(userList.stream().map(User::getUpdateUser).collect(Collectors.toList()));

            var roleIdList = userList.stream().map(User::getRole).distinct().collect(Collectors.toList());

            var roleList = roleRepository.getByIds(roleIdList);
            var operatorList = userRepository.getListByCodes(operatorCodeList.stream().distinct().collect(Collectors.toList()));

            for(var item : userList){
                var insertUser = operatorList.stream().filter(r->r.getInsertUser().equals(item.getInsertUser())).findFirst().orElse(null);
                var updateUser = operatorList.stream().filter(r->r.getUpdateUser().equals(item.getUpdateUser())).findFirst().orElse(null);
                var role = roleList.stream().filter(r->r.getId() == item.getRole()).findFirst().orElse(null);
                var insertUserName = insertUser==null?"":insertUser.getName();
                var updateUserName = updateUser==null?"":updateUser.getName();
                var roleName = role==null?"":role.getName();
                result.add(new UserPageInfoModel(item.getCode(),item.getName(),item.getRole(),roleName,item.getPic(),
                        item.getColor(),item.getInsertUser(),insertUserName,item.getInsertDate(),
                        item.getUpdateUser(),updateUserName,item.getUpdateDate()));
            }
        }
        return result;
    }

    @Override
    public Page<UserPageInfoModel> getPageList(GetPageUserReqModel getPageUserReq) {
        var pageList = new ArrayList<UserPageInfoModel>();
        var result = new Page<UserPageInfoModel>();
        result.setPageSize(getPageUserReq.getPageLength());
        result.setPageNumber(getPageUserReq.getPageStart());
        var queryCondition = new QueryCondition(getPageUserReq.getPageStart(),getPageUserReq.getPageLength(),getPageUserReq.getOrderColumn(),getPageUserReq.getOrderType());
        var listCount = userRepository.getPageCount(getPageUserReq.getCondition(),getPageUserReq.getRole());
        if(listCount>0){
            var userList = userRepository.getPageList(getPageUserReq.getCondition(),getPageUserReq.getRole(),queryCondition);
            var operatorCodeList = userList.stream().map(User::getInsertUser).collect(Collectors.toList());
            operatorCodeList.addAll(userList.stream().map(User::getUpdateUser).collect(Collectors.toList()));

            var roleIdList = userList.stream().map(User::getRole).distinct().collect(Collectors.toList());

            var roleList = roleRepository.getByIds(roleIdList);
            var operatorList = userRepository.getListByCodes(operatorCodeList.stream().distinct().collect(Collectors.toList()));

            for(var item : userList){
                var insertUser = operatorList.stream().filter(r->r.getInsertUser().equals(item.getInsertUser())).findFirst().orElse(null);
                var updateUser = operatorList.stream().filter(r->r.getUpdateUser().equals(item.getUpdateUser())).findFirst().orElse(null);
                var role = roleList.stream().filter(r->r.getId() == item.getRole()).findFirst().orElse(null);
                var insertUserName = insertUser==null?"":insertUser.getName();
                var updateUserName = updateUser==null?"":updateUser.getName();
                var roleName = role==null?"":role.getName();
                pageList.add(new UserPageInfoModel(item.getCode(),item.getName(),item.getRole(),roleName,item.getPic(),
                        item.getColor(),item.getInsertUser(),insertUserName,item.getInsertDate(),
                        item.getUpdateUser(),updateUserName,item.getUpdateDate()));
            }
        }
        result.setTotalRecord(listCount);
        result.setTotalPage(listCount/getPageUserReq.getPageLength());
        result.setList(pageList);
        return result;
    }

    @Override
    public void add(CreateUserReqModel model) {

    }

    @Override
    public void delete(String code) {

    }

    @Override
    public void edit(User entity) {

    }

}
