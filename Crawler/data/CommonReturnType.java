1
https://raw.githubusercontent.com/ryewen/quickbuy/master/src/main/java/com/loststars/quickbuy/response/CommonReturnType.java
package com.loststars.quickbuy.response;

public class CommonReturnType {

    private String status;
    
    private Object data;
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static CommonReturnType createSuccess(Object data) {
        return create(data, "success");
    }
    
    public static CommonReturnType createFail(Object data) {
        return create(data, "fail");
    }
    
    private static CommonReturnType create(Object data, String status) {
        CommonReturnType commonRetrunType = new CommonReturnType();
        commonRetrunType.setStatus(status);
        commonRetrunType.setData(data);
        return commonRetrunType;
    }
}
