11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-order/src/main/java/com/hyf/mtcc/demo/order/request/DecreaseAccountReq.java
package com.hyf.mtcc.demo.order.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class DecreaseAccountReq implements Serializable {

    private static final long serialVersionUID = 7087168388086184808L;

    @NotNull
    private String orderId;

    @NotNull
    private String username;

    @NotNull
    private BigDecimal amount;

}
