package com.binla.bcs.model.user.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserDataModel extends UserModel{
    private String password;
    private String salt;
    private String insertUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertDate;
    private String updateUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;
    public UserDataModel(String code,String name,int role,String pic,String color,String password,String salt,
                         String insertUser,Date insertDate,String updateUser,Date updateDate ){
        super(code,name,role,pic,color);
        this.password = password;
        this.salt = salt;
        this.insertUser = insertUser;
        this.insertDate = insertDate;
        this.updateUser = updateUser;
        this.updateDate = updateDate;
    }
}
