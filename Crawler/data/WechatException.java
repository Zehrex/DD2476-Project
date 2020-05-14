9
https://raw.githubusercontent.com/developer-weapons/wechat-spring-boot-starter/master/src/main/java/com/github/developer/weapons/exception/WechatException.java
package com.github.developer.weapons.exception;

public class WechatException extends RuntimeException {
    private String message;

    public WechatException(String message) {
        this.message = message;
    }


    public WechatException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
