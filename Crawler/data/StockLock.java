11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-stock/src/main/java/com/hyf/mtcc/demo/stock/bean/StockLock.java
package com.hyf.mtcc.demo.stock.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "tbl_stock_lock")
public class StockLock implements Serializable {

    private static final long serialVersionUID = -3574878072958127705L;

    @TableId
    private String id;

    private String productId;

    private String orderId;

    private Integer lockStock;

    private Date createTime;

    private Date updateTime;


}
