11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-stock/src/main/java/com/hyf/mtcc/demo/stock/bean/Stock.java
package com.hyf.mtcc.demo.stock.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@TableName(value = "tbl_stock")
public class Stock implements Serializable {

    private static final long serialVersionUID = -4501938787939428308L;

    @TableId
    private String id;

    private String productName;

    private Integer totalStock;

    private Integer lockStock;

    private BigDecimal price;

    private Date createTime;

    private Date updateTime;

}
