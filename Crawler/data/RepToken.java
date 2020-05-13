2
https://raw.githubusercontent.com/Aivacom/JLYLiveChat-android/master/app/src/main/java/com/mediaroom/bean/RepToken.java
package com.mediaroom.bean;

public class RepToken {

    /**
     * code : 0
     * message : 操作成功
     * object : AAAAAgAAAEpXogWdAAU5OTAwNAABAAtjaGFubmVsTmFtZQAEdGVzdAAAAAABbUNo1VgAAAAeTY-q4sOj7tZJVInipPpiZSUUUWg
     * success : true
     */

    private int code;
    private String message;
    private String object;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
