1
https://raw.githubusercontent.com/ryewen/quickbuy/master/src/main/java/com/loststars/quickbuy/error/CommonError.java
package com.loststars.quickbuy.error;

public interface CommonError {

    public Integer getErrCode();
    
    public String getErrMsg();
    
    public CommonError setErrMsg(String errMsg);
}
