2
https://raw.githubusercontent.com/Aivacom/JLYAudio-android/master/app/src/main/java/com/mediaroom/data/ResponeModel.java
package com.mediaroom.data;

/**
 *
 * Basic Response Class
 *
 * ZH：
 * 网络回调接口
 *
 * @author Aslan chenhengfei@yy.com
 * @since 2019/12/18
 */
public class ResponeModel<T> {
    private int code;
    private String message;
    private boolean success;
    private T object;

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
