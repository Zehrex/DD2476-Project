6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/pay/service/impl/PayTradeOrderServiceImpl.java
package com.github.taoroot.taoiot.pay.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeCreateModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradeCreateRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeCreateResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.taoroot.taoiot.common.BrowserUAEnum;
import com.github.taoroot.taoiot.common.R;
import com.github.taoroot.taoiot.pay.PayProperties;
import com.github.taoroot.taoiot.pay.PayTradeStatusEnum;
import com.github.taoroot.taoiot.pay.entity.PayTradeOrder;
import com.github.taoroot.taoiot.pay.mapper.PayTradeOrderMapper;
import com.github.taoroot.taoiot.pay.service.PayTradeOrderService;
import com.ijpay.core.enums.SignType;
import com.ijpay.core.enums.TradeType;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.wxpay.WxPayApi;
import com.ijpay.wxpay.model.RefundModel;
import com.ijpay.wxpay.model.UnifiedOrderModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 商品
 *
 * @author zhiyi
 */
@Slf4j
@AllArgsConstructor
public class PayTradeOrderServiceImpl extends ServiceImpl<PayTradeOrderMapper, PayTradeOrder> implements PayTradeOrderService {

    private final PayProperties payProperties;
    private final HttpServletRequest request;
    private final AlipayClient aliPayClient;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public HashMap<String, Object> buyByAli(PayTradeOrder order) {
        order.setChannelType(BrowserUAEnum.ALIPAY.getName());
        order.setChannelMchNo(payProperties.getAli().getAppId());

        AlipayTradeCreateModel alipayTradeCreateModel = new AlipayTradeCreateModel();
        alipayTradeCreateModel.setSubject(order.getOrderSubject());
        alipayTradeCreateModel.setOutTradeNo(order.getOrderId());
        alipayTradeCreateModel.setBuyerId(order.getOpenId());
        alipayTradeCreateModel.setTimeoutExpress("1m");
        alipayTradeCreateModel.setTotalAmount(NumberUtil.div(order.getOrderAmount().toString(), "100", 2).toString());

        AlipayTradeCreateRequest alipayTradeCreateRequest = new AlipayTradeCreateRequest();
        alipayTradeCreateRequest.setNotifyUrl(payProperties.getAli().getNotifyUrl());
        alipayTradeCreateRequest.setBizModel(alipayTradeCreateModel);
        try {
            AlipayTradeCreateResponse alipayTradeCreateResponse = aliPayClient.execute(alipayTradeCreateRequest);

            HashMap<String, Object> map = new HashMap<>();
            map.put("type", order.getChannelType());
            map.put("order", order);
            map.put("params", alipayTradeCreateResponse.getTradeNo());

            order.setChannelOrderNo(alipayTradeCreateResponse.getTradeNo());
            order.updateById();
            return map;
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HashMap<String, Object> buyByWx(PayTradeOrder order) {
        order.setChannelType(BrowserUAEnum.WECHAT.getName());
        PayProperties.WxPayApiConfig wxPay = payProperties.getWx();
        order.setChannelMchNo(wxPay.getMchId());

        // 微信支付订单
        Map<String, String> params = UnifiedOrderModel.builder()
                .body(order.getOrderSubject())
                .spbill_create_ip(ServletUtil.getClientIP(request))
                .total_fee(order.getOrderAmount().toString())
                .openid(order.getOpenId())
                .nonce_str(IdUtil.fastSimpleUUID())
                .trade_type(TradeType.JSAPI.getTradeType())
                .notify_url(wxPay.getNotifyUrl())
                .out_trade_no(order.getOrderId())
                .appid(wxPay.getAppId())
                .mch_id(wxPay.getMchId())
                .build().createSign(wxPay.getPartnerKey(), SignType.MD5);

        String xmlResult = WxPayApi.pushOrder(false, params);
        log.info("WxPayApi pushOrder: {}", xmlResult);
        Map<String, String> resultMap = WxPayKit.xmlToMap(xmlResult);
        String prepayId = resultMap.get("prepay_id");
        Map<String, String> prepayIdCreateSignMap = WxPayKit.prepayIdCreateSign(prepayId,
                wxPay.getAppId(),
                wxPay.getPartnerKey(),
                SignType.MD5
        );
        order.setChannelOrderNo(prepayId);
        order.updateById();
        HashMap<String, Object> map = new HashMap<>();
        map.put("type", order.getChannelType());
        map.put("order", order);
        map.put("params", prepayIdCreateSignMap);
        return map;
    }


    /**
     * 退款
     *
     * @param tradeOrder
     * @return
     */
    @Override
    public R refund(PayTradeOrder tradeOrder) {
        if (tradeOrder.getChannelType().equals(BrowserUAEnum.WECHAT.getName())) {
            // 微信退款
            PayProperties.WxPayApiConfig wxPayApiConfig = payProperties.getWx();
            Map<String, String> params = RefundModel.builder()
                    .appid(wxPayApiConfig.getAppId())
                    .mch_id(wxPayApiConfig.getMchId())
                    .nonce_str(String.valueOf(System.currentTimeMillis()))
                    .out_trade_no(tradeOrder.getOrderId())
                    .out_refund_no(String.valueOf(System.currentTimeMillis()))
                    .total_fee(tradeOrder.getOrderAmount().toString())
                    .refund_fee(tradeOrder.getOrderAmount().toString())
                    .build()
                    .createSign(wxPayApiConfig.getPartnerKey(), SignType.MD5);

            String refund = WxPayApi.orderRefund(false,
                    params,
                    wxPayApiConfig.getCertPath(),
                    wxPayApiConfig.getMchId()
            );
            log.debug(refund);
            tradeOrder.setOrderStatus(PayTradeStatusEnum.REFUND_COMPLETE.getStatus());
            tradeOrder.updateById();
            return null;
        } else if (tradeOrder.getChannelType().equals(BrowserUAEnum.ALIPAY.getName())) {
            // 支付宝退款
            AlipayTradeRefundModel model = new AlipayTradeRefundModel();
            model.setOutTradeNo(tradeOrder.getOrderId());
            model.setRefundAmount(NumberUtil.div(tradeOrder.getOrderAmount().toString(), "100", 2).toString());
            try {
                AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
                request.setBizModel(model);
                // 生成支付参数，并且直接跳转到支付界面
                AlipayTradeRefundResponse alipayTradeRefundResponse = aliPayClient.execute(request);
                log.debug("支付包退款： {}", alipayTradeRefundResponse);
            } catch (AlipayApiException e) {
                log.error("支付宝退款失败", e);
            }
            return R.ok("支付宝退款");
        } else {
            return R.err("不支持退款类型");
        }
    }
}
