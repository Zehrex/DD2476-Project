9
https://raw.githubusercontent.com/developer-weapons/wechat-spring-boot-starter/master/src/main/java/com/github/developer/weapons/model/component/ComponentAuthInfo.java
package com.github.developer.weapons.model.component;

import lombok.Data;

@Data
public class ComponentAuthInfo {
    /**
     * 授权订阅号的 token
     */
    private String token;

    /**
     * 授权订阅号的账号 id
     */
    private String appId;
}
