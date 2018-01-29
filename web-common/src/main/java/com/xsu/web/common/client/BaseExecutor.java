package com.xsu.web.common.client;

public abstract class BaseExecutor<Resp extends BaseResponse,Req extends BaseRequest<Resp>> {
    protected abstract Resp execute(BaseRequest<Resp> Req);
}
