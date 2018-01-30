package com.xsu.web.common.client;

public abstract class BaseExecutor<TResp extends BaseResponse,TReq extends BaseRequest<?>> {
    protected abstract TResp execute(TReq request);
}
