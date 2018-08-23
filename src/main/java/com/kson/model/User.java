package com.kson.model;

import com.alibaba.fastjson.annotation.JSONField;

public class User {

    private Integer id;

    @JSONField(name = "user_name")
    private String userName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "userName=" + userName + ";id=" + id;
    }
}
