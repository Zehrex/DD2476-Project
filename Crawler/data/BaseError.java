2
https://raw.githubusercontent.com/Aivacom/JLYAudio-android/master/app/src/main/java/com/mediaroom/data/BaseError.java
package com.mediaroom.data;

/**
 *
 * Basic Error information Class
 *
 * ZH：
 * 错误异常类
 *
 * @author Aslan chenhengfei@yy.com
 * @since 2019/12/18
 */
public class BaseError {
    private int code;
    private String message;

    public BaseError() {
    }

    public BaseError(int code, String message) {
        this.code = code;
        this.message = message;
    }

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
}
