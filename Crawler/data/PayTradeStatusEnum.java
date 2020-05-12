6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/pay/PayTradeStatusEnum.java
package com.github.taoroot.taoiot.pay;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态
 */
@Getter
@AllArgsConstructor
public enum PayTradeStatusEnum {
    /**
     * 订单初始 下单
     */
    INIT("0", "订单创建"),

    /**
     * 订单支付成功
     */
    SUCCESS("1", "支付成功"),

    /**
     * 订单支付完成
     */
    COMPLETE("2", "业务完成"),

    /**
     * 订单退款中
     */
    REFUND_SUCCESS("3", "请求退款"),

    /**
     * 订单退款
     */
    REFUND_COMPLETE("4", "退款完成"),

    /**
     * 订单支付失败
     */
    FAIL("-1", "失败");

    /**
     * 状态
     */
    private String status;
    /**
     * 描述
     */
    private String description;
}
