6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/pay/PayProperties.java
package com.github.taoroot.taoiot.pay;

import com.github.taoroot.taoiot.common.Const;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : zhiyi
 * Date: 2020/2/17
 */
@Data
@ConfigurationProperties(prefix = Const.PREFIX + ".pay")
public class PayProperties {

    /**
     * 支付宝参数
     */
    private AliPayApiConfig ali = new AliPayApiConfig();

    /**
     * 微信参数
     */
    private WxPayApiConfig wx = new WxPayApiConfig();

    /**
     * 微信配置
     */
    @Data
    public static class WxPayApiConfig {
        /**
         * 交易编号
         */
        public final static String TRADE_NO_KEY = "out_trade_no";

        /**
         * 支付状态
         */
        public final static String TRADE_STATUS = "trade_status";

        /**
         * 返回码
         */
        public final static String RESULT_CODE = "result_code";

        /**
         * 公众平台id
         */
        private String appId;
        /**
         * 商户号
         */
        private String mchId;
        /**
         * 商户密钥
         */
        private String partnerKey;
        /**
         * 商户证书路径
         */
        private String certPath;
        /**
         * 异步通知地址
         */
        private String notifyUrl;
    }

    /**
     * 支付宝配置
     */
    @Data
    public static class AliPayApiConfig {
        /**
         * 交易编号
         */
        public final static String TRADE_NO_KEY = "out_trade_no";
        /**
         * 支付状态
         */
        public final static String TRADE_STATUS = "trade_status";
        /**
         * appId
         */
        private String appId;
        /**
         * 密钥
         */
        private String privateKey;
        /**
         * 公钥
         */
        private String publicKey;
        /**
         * 异步通知地址
         */
        private String notifyUrl;
    }
}
