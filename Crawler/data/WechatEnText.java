9
https://raw.githubusercontent.com/developer-weapons/wechat-spring-boot-starter/master/src/main/java/com/github/developer/weapons/model/WechatEnText.java
package com.github.developer.weapons.model;

import lombok.Data;

@Data
public class WechatEnText {
    private String Encrypt;
    private String MsgSignature;
    private String TimeStamp;
    private String Nonce;
}
