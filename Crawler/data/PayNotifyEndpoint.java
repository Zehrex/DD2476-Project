6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/pay/PayNotifyEndpoint.java
package com.github.taoroot.taoiot.pay;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.StrUtil;
import com.alipay.api.internal.util.AlipaySignature;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.taoroot.taoiot.common.BrowserUAEnum;
import com.github.taoroot.taoiot.pay.entity.PayNotifyRecord;
import com.github.taoroot.taoiot.pay.entity.PayTradeOrder;
import com.github.taoroot.taoiot.pay.service.PayNotifyRecordService;
import com.github.taoroot.taoiot.pay.service.PayTradeOrderService;
import com.github.taoroot.taoiot.security.annotation.NotAuth;
import com.github.taoroot.taoiot.swagger.NotSwagger;
import com.ijpay.core.kit.HttpKit;
import com.ijpay.core.kit.PayKit;
import com.ijpay.core.kit.WxPayKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * 异步通知记录
 * 支付宝 / 微信 同一个订单，可能会重复回调多次，需要去重处理
 * 所以一般操作
 * 1. 解析
 * 2. 去重
 * 3. 验签
 * 4. 业务 / 更新订单状态 / 设备出库
 * 5. 保存回调记录
 */
@Api(tags = "支付异步通知")
@Slf4j
@AllArgsConstructor
@RequestMapping("/pay/notify")
@NotAuth
@NotSwagger
public class PayNotifyEndpoint {
    private final PayTradeOrderService payTradeOrderService;
    private final PayNotifyRecordService payNotifyRecordService;
    private final PayProperties payProperties;

    @ApiOperation("支付异步回调")
    @SneakyThrows
    @PostMapping("/ali")
    public String aliCallback(HttpServletRequest request, HttpServletResponse response) {
        // 解析回调信息, 转map
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        // 去重
        String tradeNO = params.get(PayProperties.AliPayApiConfig.TRADE_NO_KEY);
        int count = payNotifyRecordService.count(Wrappers.<PayNotifyRecord>lambdaQuery()
                .eq(PayNotifyRecord::getOrderNo, tradeNO));

        if (count > 0) {
            return "success";
        }

        // 验签
        String callReq = MapUtil.join(params, StrUtil.DASHED, StrUtil.DASHED);
        AlipaySignature.rsaCheckV1(params, payProperties.getAli().getPublicKey(), CharsetUtil.UTF_8, "RSA2");
        log.info("支付宝发起回调: {}", params);

        // 解析
        String orderNo = params.get(PayProperties.AliPayApiConfig.TRADE_NO_KEY);

        PayTradeOrder tradeOrder = payTradeOrderService.getOne(
                Wrappers.<PayTradeOrder>lambdaQuery()
                        .eq(PayTradeOrder::getOrderId, orderNo));

        String tradeStatus = params.get(PayProperties.AliPayApiConfig.TRADE_STATUS);
        if ("TRADE_SUCCESS".equals(tradeStatus)) {
            tradeOrder.setOrderStatus(PayTradeStatusEnum.SUCCESS.getStatus());
        }

        tradeOrder.setOrderPaySuccessTime(MapUtil.getStr(params, "gmt_payment"));
        tradeOrder.setChannelType(BrowserUAEnum.ALIPAY.getName());
        tradeOrder.setChannelOrderNo(params.get("trade_no"));
        payTradeOrderService.updateById(tradeOrder);

        // 记录
        PayNotifyRecord payNotifyRecord = new PayNotifyRecord();
        payNotifyRecord.setNotifyId(params.get("notify_id"));
        payNotifyRecord.setOrderNo(orderNo);
        payNotifyRecord.setRequest(MapUtil.join(params, StrUtil.DASHED, StrUtil.DASHED));
        payNotifyRecord.setResponse(callReq);
        payNotifyRecordService.save(payNotifyRecord);

        return "success";
    }

    @ApiOperation("微信支付回调")
    @SneakyThrows
    @ResponseBody
    @PostMapping("/wx")
    public String wxCallback(HttpServletRequest request) {
        // 解析
        String xmlMsg = HttpKit.readData(request);
        log.info("微信订单回调信息:{}", xmlMsg);
        Map<String, String> params = PayKit.xmlToMap(xmlMsg);

        // 去重
        String tradeNO = params.get(PayProperties.WxPayApiConfig.TRADE_NO_KEY);
        int count = payNotifyRecordService.count(Wrappers.<PayNotifyRecord>lambdaQuery()
                .eq(PayNotifyRecord::getOrderNo, tradeNO));
        if (count > 0) {
            return null;
        }

        // 验签
        if (!WxPayKit.verifyNotify(params, payProperties.getWx().getPartnerKey())) {
            log.warn("微信支付回调验签失败 {}", params);
            return "success";
        }

        // 解析
        String tradeStatus = EnumUtil.fromString(PayTradeStatusEnum.class, params.get(PayProperties.WxPayApiConfig.RESULT_CODE)).getStatus();
        String orderNo = params.get(PayProperties.WxPayApiConfig.TRADE_NO_KEY);
        PayTradeOrder tradeOrder = payTradeOrderService.getOne(Wrappers.<PayTradeOrder>lambdaQuery().eq(PayTradeOrder::getOrderId, orderNo));
        if (tradeOrder == null) {
            return "success";
        }

        tradeOrder.setOrderStatus(tradeStatus);
        Date date = new SimpleDateFormat("yyyyMMddHHmmss").parse(MapUtil.getStr(params, "time_end"));
        tradeOrder.setOrderPaySuccessTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        tradeOrder.setChannelOrderNo(params.get("transaction_id"));
        tradeOrder.setChannelErrMsg(params.get("err_code_des"));
        tradeOrder.setChannelErrCode(params.get("err_code"));
        payTradeOrderService.updateById(tradeOrder);

        // 记录
        PayNotifyRecord payNotifyRecord = new PayNotifyRecord();
        payNotifyRecord.setNotifyId(params.get("transaction_id"));
        payNotifyRecord.setOrderNo(orderNo);
        payNotifyRecord.setRequest(MapUtil.join(params, StrUtil.DASHED, StrUtil.DASHED));
        payNotifyRecord.setResponse(tradeStatus);
        payNotifyRecordService.save(payNotifyRecord);

        return "success";
    }

    @ApiOperation("支付宝退款异步回调")
    @SneakyThrows
    @PostMapping("/refund/ali")
    public String aliRefundCallback(HttpServletRequest request, HttpServletResponse response) {
        return "success";
    }

    @ApiOperation("微信退款异步回调")
    @SneakyThrows
    @PostMapping("/refund/wx")
    public String wxRefundCallback(HttpServletRequest request, HttpServletResponse response) {
        return "success";
    }
}
