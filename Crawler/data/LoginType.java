6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/security/LoginType.java
package com.github.taoroot.taoiot.security;

import lombok.AllArgsConstructor;

/**
 * @author : zhiyi
 * Date: 2020/5/5
 */
@AllArgsConstructor
public enum LoginType {
    /**
     * PASS
     */
    PASS("密码登录"),
    /**
     * SMS
     */
    SMS("短信登录"),
    /**
     * WX
     */
    WX("微信登录"),
    /**
     * ALIPAY
     */
    ALIPAY("支付宝登录");

    private String type;
}
