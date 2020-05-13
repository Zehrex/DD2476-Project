2
https://raw.githubusercontent.com/gavin-yyj/vhr-/master/swagger/src/main/java/com/macro/mall/tiny/api/IErrorCode.java
package com.macro.mall.tiny.api;

/**
 * 封装API的错误码
 * Created by macro on 2019/4/19.
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
