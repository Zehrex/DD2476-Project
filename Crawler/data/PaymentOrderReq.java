11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-order/src/main/java/com/hyf/mtcc/demo/order/request/PaymentOrderReq.java
package com.hyf.mtcc.demo.order.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class PaymentOrderReq implements Serializable {

    private String orderId;

    @NotNull
    private String username;

    @NotNull
    private String productName;

    @NotNull
    private Integer count;

}
