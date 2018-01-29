package com.xsu.web.server.sdk.user.resp;

import com.xsu.web.common.client.BaseResponse;
import com.xsu.web.server.sdk.user.domain.User;

public class UserLoginResponse extends BaseResponse{
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
