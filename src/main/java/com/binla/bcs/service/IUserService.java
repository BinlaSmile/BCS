package com.binla.bcs.service;

import com.binla.bcs.domain.Page;
import com.binla.bcs.model.user.request.*;
import com.binla.bcs.model.user.response.UserDataModel;
import com.binla.bcs.model.user.response.UserInfoModel;
import com.binla.bcs.model.user.response.UserModel;
import com.binla.bcs.model.user.response.UserPageInfoModel;

import java.util.List;

public interface IUserService {
    UserModel getByCode(String code);
    UserDataModel getDataByCode(String code);
    UserInfoModel getInfoByCode(String code);

    List<UserPageInfoModel> getList(GetUserReqModel getUserReq);
    Page<UserPageInfoModel> getPageList(GetPageUserReqModel getPageUserReq);

    void add(CreateUserReqModel model);
    void edit(String code, EditUserReqModel model);
    void editPassword(String code, ChangePasswordReqModel model);
    void delete(String code);
}
