package com.binla.bcs.domain.common;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity {
    private String insertUser;
    private Date insertDate;
    private String updateUser;
    private Date updateDate;
    private static final long serialVersionUID = 7766184319541530720L;

    public Date getInsertDate() {
        return insertDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public String getInsertUser() {
        return insertUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public void setInsertUser(String insertUser) {
        this.insertUser = insertUser;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
