6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/common/BrowserUAEnum.java
package com.github.taoroot.taoiot.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 浏览器类型判断
 * @author zhiyi
 */
@AllArgsConstructor
public enum BrowserUAEnum {
    /**
     * 支付宝支付
     */
    ALIPAY("ALIPAY", "Alipay"),

    /**
     * 微信公众号支付
     */
    WECHAT("WECHAT", "MicroMessenger");


    /**
     * 描述
     */
    @Getter
    private String name;

    /**
     * 浏览器标志
     */
    @Getter
    private String browserUserAgent;

}