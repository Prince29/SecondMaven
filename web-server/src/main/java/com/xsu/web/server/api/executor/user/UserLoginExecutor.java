package com.xsu.web.server.api.executor.user;

import com.xsu.web.common.client.BaseExecutor;
import com.xsu.web.server.sdk.user.domain.User;
import com.xsu.web.server.sdk.user.req.UserLoginRequest;
import com.xsu.web.server.sdk.user.resp.UserLoginResponse;
import org.springframework.stereotype.Component;

@Component("v1.user.login")
public class UserLoginExecutor extends BaseExecutor<UserLoginResponse,UserLoginRequest>{

    protected UserLoginResponse execute(UserLoginRequest request) {
        UserLoginResponse resp=new UserLoginResponse();
        User user=new User();
        user.setUserId(1L);
        user.setUserName("test");
        resp.setUser(user);
        return resp;
    }
}
