6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/pay/service/PayTradeOrderService.java
package com.github.taoroot.taoiot.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.taoroot.taoiot.common.R;
import com.github.taoroot.taoiot.pay.entity.PayTradeOrder;

import java.util.HashMap;

/**
 * 商品
 *
 * @author flizi
 * @date 2019-05-28 23:58:27
 */
public interface PayTradeOrderService extends IService<PayTradeOrder> {

    /**
     * 通过支付宝 购买
     *
     * @param payTradeOrder 商品订单
     */
    HashMap<String, Object> buyByAli(PayTradeOrder payTradeOrder);

    /**
     * 通过微信 购买
     *
     * @param payTradeOrder 商品订单
     * @return 微信生成的订单参数
     */
    HashMap<String, Object> buyByWx(PayTradeOrder payTradeOrder);

    /**
     * 退款
     *
     * @param tradeOrder
     * @return
     */
    R refund(PayTradeOrder tradeOrder);
}
