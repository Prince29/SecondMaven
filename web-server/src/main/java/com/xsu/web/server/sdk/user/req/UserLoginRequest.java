package com.xsu.web.server.sdk.user.req;

import com.xsu.web.common.client.BaseRequest;
import com.xsu.web.common.client.CheckResult;
import com.xsu.web.server.sdk.user.resp.UserLoginResponse;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class UserLoginRequest extends BaseRequest<UserLoginResponse>{
    private String userName;
    private String password;


    protected String getMethodName() {
        return "1.user.login";
    }

    protected Map<String, Object> getParamsMap() {
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("userName",userName);
        map.put("password",password);
        return map;
    }

    protected CheckResult check() {
        if(StringUtils.isEmpty(userName)){
            throw new RuntimeException("userName empty");
        }
        if(StringUtils.isEmpty(password)){
            throw new RuntimeException("password empty");
        }
        return CheckResult.SUCCESS();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
