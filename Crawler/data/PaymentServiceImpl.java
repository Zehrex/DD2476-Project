11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-order/src/main/java/com/hyf/mtcc/demo/order/service/impl/PaymentServiceImpl.java
package com.hyf.mtcc.demo.order.service.impl;

import com.hyf.mtcc.demo.order.bean.Order;
import com.hyf.mtcc.demo.order.dao.OrderMapper;
import com.hyf.mtcc.demo.order.inter.AccountInter;
import com.hyf.mtcc.demo.order.inter.StockInter;
import com.hyf.mtcc.demo.order.request.DecreaseAccountReq;
import com.hyf.mtcc.demo.order.request.DecreaseStockReq;
import com.hyf.mtcc.demo.order.request.PaymentOrderReq;
import com.hyf.mtcc.demo.order.service.PaymentService;
import com.yf.mtcc.common.exception.MtccException;
import com.yf.mtcc.core.annotation.Mtcc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Descrtption PaymentServiceImpl
 * @Author Elvis
 * @Date 2019/9/28
 **/
@Slf4j
@Service
@Transactional(readOnly = true)
@SuppressWarnings("all")
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private StockInter stockInter;

    @Resource
    private AccountInter accountInter;

    @Resource
    private OrderMapper orderMapper;

    @Mtcc(confirmMethod = "paymentConfirm", cancelMethod = "paymentCancel")
    @Override
    public void payment(PaymentOrderReq req) {
        log.info("==================payment开始=======================");
        Order order = orderMapper.selectById(req.getOrderId());
        order.setStatus(2);
        order.setUpdateTime(new Date());
        orderMapper.updateById(order);

        String remoteResult = "failure";
        //扣减库存
        DecreaseStockReq stockReq = new DecreaseStockReq();
        stockReq.setOrderId(order.getId());
        stockReq.setProductName(req.getProductName());
        stockReq.setStock(req.getCount());
//        发起远程调用的feign接口都需要标记上分布式事务注解
        remoteResult = stockInter.decreaseStock(stockReq);
        if ("failure".equals(remoteResult)) {
            throw new RuntimeException("decreaseStock error");
        }

        //扣减账户余额
        DecreaseAccountReq accountReq = new DecreaseAccountReq();
        accountReq.setOrderId(order.getId());
        accountReq.setUsername(req.getUsername());
        accountReq.setAmount(order.getAmount());
        remoteResult = accountInter.decreaseAccount(accountReq);
        if ("failure".equals(remoteResult)) {
            throw new RuntimeException("decreaseAccount error");
        }
        log.info("==================payment结束=======================");
    }

    /**
     * paymentConfirm 失败时需要抛出异常
     *
     * @param req
     */
    @Transactional(rollbackFor = Exception.class)
    public void paymentConfirm(PaymentOrderReq req) {
        log.info("==================paymentConfirm开始=======================");
        Order dbOrder = orderMapper.selectById(req.getOrderId());
        if (dbOrder != null) {
            dbOrder.setStatus(4);
            dbOrder.setUpdateTime(new Date());
            int influence = orderMapper.updateById(dbOrder);
            if (influence < 1) {
                throw new MtccException("paymentConfirm exception");
            }
        } else {
            log.warn("confirm找不到订单，req:{}", req);
        }
        log.info("==================paymentConfirm结束=======================");
    }

    /**
     * paymentCancel 失败时需要抛出异常
     *
     * @param req
     */
    @Transactional(rollbackFor = Exception.class)
    public void paymentCancel(PaymentOrderReq req) {
        log.info("==================paymentCancel开始=======================");
        Order dbOrder = orderMapper.selectById(req.getOrderId());
        if (dbOrder != null) {
            dbOrder.setStatus(3);
            dbOrder.setUpdateTime(new Date());
            int influence = orderMapper.updateById(dbOrder);
            if (influence < 1) {
                throw new MtccException("paymentCancel exception");
            }
        } else {
            log.warn("cancel找不到订单，req:{}", req);
        }
        log.info("==================paymentCancel结束=======================");
    }

}
