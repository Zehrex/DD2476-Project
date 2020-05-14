9
https://raw.githubusercontent.com/developer-weapons/wechat-spring-boot-starter/master/src/main/java/com/github/developer/weapons/model/component/ComponentAuthorizerInfo.java
package com.github.developer.weapons.model.component;

import lombok.Data;

@Data
public class ComponentAuthorizerInfo {
    private String userName;
    private String authorizerAppid;
    private String nickName;
    private String headImg;
    private String qrcodeUrl;
    private String authorizerRefreshToken;

    // 认证状态
    private Integer verifyTypeInfo;


}
