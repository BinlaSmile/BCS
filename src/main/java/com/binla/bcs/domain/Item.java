package com.binla.bcs.domain;

import com.binla.bcs.domain.common.BaseEntity;

public class Item extends BaseEntity {
    private int iid;
    private String code;
    private String name;
    private String pic;
    private int amount;



    public Item(){}
    public Item(int iid,String code,String name,String pic,int amount){
        this.iid = iid;
        this.code = code;
        this.name = name;
        this.pic = pic;
        this.amount = amount;
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
