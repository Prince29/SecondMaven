package com.xsu.web.common.client;

import com.xsu.web.common.conf.ErrorCodeConf;

public class CheckResult {
    private String flag;
    private String code;
    private String msg;

    public static CheckResult SUCCESS(){
        CheckResult checkResult=new CheckResult();
        checkResult.setFlag(ErrorCodeConf.FLAG_SUCCESS);
        checkResult.setCode(ErrorCodeConf.SUCCESS);
        return checkResult;
    }

    public static CheckResult FAILURE(){
        CheckResult checkResult=new CheckResult();
        checkResult.setFlag(ErrorCodeConf.FLAG_FAILURE);
        checkResult.setCode(ErrorCodeConf.FAILURE);
        return checkResult;
    }

    public static CheckResult FAILURE(String msg){
        CheckResult checkResult=new CheckResult();
        checkResult.setFlag(ErrorCodeConf.FLAG_FAILURE);
        checkResult.setCode(ErrorCodeConf.FAILURE);
        checkResult.setMsg(msg);
        return checkResult;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
