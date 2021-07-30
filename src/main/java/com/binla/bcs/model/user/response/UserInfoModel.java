package com.binla.bcs.model.user.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfoModel extends UserModel{
    private String roleName;
    public UserInfoModel(String code,String name,int role,String roleName,String pic,String color){
        super(code,name,role,pic,color);
        this.roleName = roleName;
    }
}
