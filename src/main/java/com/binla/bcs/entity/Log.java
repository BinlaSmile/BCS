package com.binla.bcs.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Log {
    private int id;
    private int type;
    private String content;
    private String insertUser;
    private Date insertDate;

    public Log(int type,String content,String insertUser,Date insertDate){
        this.type = type;
        this.content = content;
        this.insertUser = insertUser;
        this.insertDate = insertDate;
    }
}
