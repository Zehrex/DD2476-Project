9
https://raw.githubusercontent.com/developer-weapons/wechat-spring-boot-starter/master/src/main/java/com/github/developer/weapons/model/component/ComponentVerifyInfo.java
package com.github.developer.weapons.model.component;

import lombok.Data;

@Data
public class ComponentVerifyInfo {
    private String signature;
    private String timestamp;
    private String nonce;
    private String body;
}