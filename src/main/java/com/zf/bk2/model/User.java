package com.zf.bk2.model;

import lombok.Data;

@Data
public class User {
    private Long userId;

    private String userName;

    private String userPwd;

    private Integer userType;

    public User(Long userId, String userName, String userPwd,String checkuserPwd, Integer userType) {
        this.userId = userId;
        this.userName = userName;
        this.userPwd = userPwd;
        this.userType = userType;
    }

    public User() {
        super();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}