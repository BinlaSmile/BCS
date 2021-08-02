package com.binla.bcs.service.impl;

import com.binla.bcs.core.BizException;
import com.binla.bcs.domain.Page;
import com.binla.bcs.domain.QueryCondition;
import com.binla.bcs.domain.enums.CodeMsg;
import com.binla.bcs.entity.Log;
import com.binla.bcs.entity.Role;
import com.binla.bcs.entity.User;
import com.binla.bcs.entity.common.BaseEntity;
import com.binla.bcs.model.user.request.*;
import com.binla.bcs.model.user.response.UserDataModel;
import com.binla.bcs.model.user.response.UserInfoModel;
import com.binla.bcs.model.user.response.UserModel;
import com.binla.bcs.model.user.response.UserPageInfoModel;
import com.binla.bcs.repository.ILogRepository;
import com.binla.bcs.repository.IRoleRepository;
import com.binla.bcs.repository.IUserRepository;
import com.binla.bcs.service.IAuthService;
import com.binla.bcs.service.IUserService;
import com.binla.bcs.utils.EncryptUtil;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private ILogRepository logRepository;

    @Autowired
    private IAuthService authService;

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
        //检查用户代码是否重复
        var entity = userRepository.getByCode(model.getCode());
        if(entity!=null)
            throw new BizException(CodeMsg.USER_EXISTS);

        var user = new User();
        user.setCode(model.getCode());
        user.setName(model.getName());

        //随机生成一个uuid作为用户的加密盐
        var salt = UUID.randomUUID().toString().replaceAll("-","");
        //使用密码+加密盐生成加密后的密码
        var eu = EncryptUtil.getInstance();
        var enPassword = eu.MD5(model.getPassword(),salt);
        user.setPassword(enPassword);
        user.setSalt(salt);
        user.setPic(model.getPic());
        user.setColor(model.getColor());
        user.setRole(model.getRole());

        var currentUserCode =authService.getCurrentUserCode();
        var dateNow = new Date();
        user.setInsertUser(currentUserCode);
        user.setInsertDate(dateNow);
        user.setUpdateUser(currentUserCode);
        user.setUpdateDate(dateNow);

        userRepository.insert(user);
        //日志
        logRepository.insert(new Log(2,"创建用户["+model.getCode()+"]",currentUserCode,dateNow));
    }

    @Override
    public void edit(String code, EditUserReqModel model) {
        //检查用户是否存在
        var entity = userRepository.getByCode(code);
        if(entity == null)
            throw new BizException(CodeMsg.USER_NOT_EXISTS);

        var user = new User();
        user.setCode(entity.getCode());
        user.setName(model.getName());
        user.setPassword(entity.getPassword());
        user.setSalt(entity.getSalt());
        user.setColor(model.getColor());
        user.setPic(model.getPic());
        user.setRole(model.getRole());
        user.setInsertUser(entity.getInsertUser());
        user.setInsertDate(entity.getInsertDate());
        var currentUserCode =authService.getCurrentUserCode();
        var dateNow = new Date();
        user.setUpdateUser(currentUserCode);
        user.setUpdateDate(dateNow);
        userRepository.update(user);
        //日志
        var logStr = new StringBuilder("修改用户["+code+"]");
        if(!entity.getName().equals(model.getName()))
            logStr.append(",用户名由:["+entity.getName()+"] 变更为: ["+model.getName()+"]");
        if(!entity.getColor().equals(model.getColor()))
            logStr.append(",颜色标识由:["+entity.getColor()+"] 变更为: ["+model.getColor()+"]");
        if(!entity.getPic().equals(model.getPic()))
            logStr.append(",变更头像");
        if(entity.getRole() != model.getRole()){
            var roleIdList = new ArrayList<Integer>();
            roleIdList.add(entity.getRole());
            roleIdList.add(model.getRole());
            var roleList = roleRepository.getByIds(roleIdList);
            var entityRole = roleList.stream().filter(r->r.getId()==entity.getRole()).findFirst().orElse(null);
            var modelRole = roleList.stream().filter(r->r.getId()==model.getRole()).findFirst().orElse(null);
            var entityRoleName = entityRole.getName();
            var modelRoleName = modelRole.getName();
            logStr.append(",用户角色由["+entity.getRole()+":"+entityRoleName+"] 变更为: ["+model.getRole()+":"+modelRoleName+"]");
        }
        logRepository.insert(new Log(2,logStr.toString(),currentUserCode,dateNow));
    }

    @Override
    public void editPassword(String code, ChangePasswordReqModel model) {
        //检查用户是否存在
        var entity = userRepository.getByCode(code);
        if(entity == null)
            throw new BizException(CodeMsg.USER_NOT_EXISTS);
        //检查新旧密码是否重复
        if(model.getNewPassword().equals(model.getOldPassword()))
            throw new BizException(CodeMsg.DUPLICATE_PASSWORD);

        var eu = EncryptUtil.getInstance();
        var enOldPassword = eu.MD5(model.getOldPassword(),entity.getSalt());
        //检查原始密码是否一致
        if(!enOldPassword.equals(entity.getPassword()))
            throw new BizException(CodeMsg.PASSWORD_ERROR);

        //随机生成一个新的uuid作为用户的加密盐
        var salt = UUID.randomUUID().toString().replaceAll("-","");
        //生成新的加密密码
        var enNewPassword = eu.MD5(model.getNewPassword(),salt);
        entity.setSalt(salt);
        entity.setPassword(enNewPassword);
        var currentUserCode =authService.getCurrentUserCode();
        var dateNow = new Date();
        entity.setUpdateUser(currentUserCode);
        entity.setUpdateDate(dateNow);
        userRepository.update(entity);
        logRepository.insert(new Log(2,"修改用户["+code+"],密码变更",currentUserCode,dateNow));
    }

    @Override
    public void delete(String code) {
        //检查用户是否存在
        var entity = userRepository.getByCode(code);
        if(entity == null)
            throw new BizException(CodeMsg.USER_NOT_EXISTS);

        var currentUserCode =authService.getCurrentUserCode();
        var dateNow = new Date();
        userRepository.deleteByCode(code);
        //日志
        logRepository.insert(new Log(2,"删除用户["+code+"]",currentUserCode,dateNow));
    }

}
