1
https://raw.githubusercontent.com/ryewen/quickbuy/master/src/main/java/com/loststars/quickbuy/error/EmBusinessError.java
package com.loststars.quickbuy.error;

public enum EmBusinessError implements CommonError {
    
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    UNKNOW_ERROR(10002, "未知错误"),
    
    USER_NOT_EXIST(20001, "用户不存在"),
    USER_LOGIN_FAIL(20002, "用户手机号或密码错误"),
    USER_NOT_LOGIN(20003, "用户未登录"),
    
    ITEM_STOCK_LIMIT(30001, "商品库存不足"),
    ;
    
    private Integer errCode;
    
    private String errMsg;
    
    private EmBusinessError(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public Integer getErrCode() {
        return errCode;
    }

    @Override
    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

}
