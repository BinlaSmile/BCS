package com.binla.bcs.model.user.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPageInfoModel extends UserInfoModel{
    private String insertUserCode;
    private String insertUserName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertDate;

    private String updateUserCode;
    private String updateUserName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;
    public UserPageInfoModel(String code,String name,int role,String roleName,String pic,String color,
                             String insertUserCode, String insertUserName, Date insertDate,
                             String updateUserCode, String updateUserName, Date updateDate){
        super(code,name,role,roleName,pic,color);
        this.insertUserCode = insertUserCode;
        this.insertUserName = insertUserName;
        this.insertDate = insertDate;
        this.updateUserCode = updateUserCode;
        this.updateUserName = updateUserName;
        this.updateDate = updateDate;
    }
}
