9
https://raw.githubusercontent.com/developer-weapons/wechat-spring-boot-starter/master/src/main/java/com/github/developer/weapons/model/official/OfficialUserInfo.java
package com.github.developer.weapons.model.official;

import lombok.Data;

@Data
public class OfficialUserInfo {
    private Integer subscribe;
    private Integer sex;
    private String openid;
    private String nickname;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String unionid;
    private String remark;
}
