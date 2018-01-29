package com.xsu.web.common.client;

public abstract class BaseRequest <T extends BaseResponse>{
    private String requestId=null;

    protected abstract String getMethodName();
}
