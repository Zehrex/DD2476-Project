2
https://raw.githubusercontent.com/jiangvin/webtank/master/util/src/main/java/com/integration/util/model/CustomException.java
package com.integration.util.model;

/**
 * @author 蒋文龙(Vin)
 * @description
 * @date 2020/5/1
 */
public class CustomException extends RuntimeException {

    public CustomException(String msg) {
        super(msg);
    }
}
