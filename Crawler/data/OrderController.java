11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-order/src/main/java/com/hyf/mtcc/demo/order/controller/OrderController.java
package com.hyf.mtcc.demo.order.controller;

import com.hyf.mtcc.demo.order.request.PaymentOrderReq;
import com.hyf.mtcc.demo.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/payment")
    public String paymentOrder(@RequestBody PaymentOrderReq req) {
        log.info("paymentOrder,req:{}", req);
        orderService.createOrder(req);
        return HttpStatus.OK.toString();
    }

}
