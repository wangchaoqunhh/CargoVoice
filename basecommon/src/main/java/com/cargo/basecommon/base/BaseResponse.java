package com.cargo.basecommon.base;

public class BaseResponse<T> {

    /**
     * respCode : 0
     * respData : {}
     * respMsg : string
     */

    private int respCode;
    private T respData;
    private String respMsg;

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public T getRespData() {
        return respData;
    }

    public void setRespData(T respData) {
        this.respData = respData;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }
}
