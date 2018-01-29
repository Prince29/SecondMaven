package com.xsu.web.server.sdk.user.domain;

public class User {
    private Long userId;
    private String userName;

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
    @Override
    public String toString(){
        return "{userId:"+userId
                +",userName:"+userName
                +"}";
    }
}
