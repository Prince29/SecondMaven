package com.xsu.web.common.client;

import java.util.Map;

public abstract class BaseRequest <T extends BaseResponse>{

    protected abstract String getMethodName();
    protected abstract Map<String,Object> getParamsMap();
    protected abstract CheckResult check();
}
