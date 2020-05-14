11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-stock/src/main/java/com/hyf/mtcc/demo/stock/request/DecreaseStockReq.java
package com.hyf.mtcc.demo.stock.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class DecreaseStockReq implements Serializable {

    private static final long serialVersionUID = -1357798122872533054L;

    @NotNull
    private String orderId;

    @NotNull
    private String productName;

    @NotNull
    private Integer stock;

}
