11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-order/src/main/java/com/hyf/mtcc/demo/order/service/PaymentService.java
package com.hyf.mtcc.demo.order.service;

import com.hyf.mtcc.demo.order.request.PaymentOrderReq;

public interface PaymentService {

    void payment(PaymentOrderReq req);

}
