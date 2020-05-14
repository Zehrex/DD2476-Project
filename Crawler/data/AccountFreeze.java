11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-account/src/main/java/com/hyf/mtcc/demo/account/bean/AccountFreeze.java
package com.hyf.mtcc.demo.account.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@TableName(value = "tbl_account_freeze")
public class AccountFreeze implements Serializable {

    private static final long serialVersionUID = 3827297878439777762L;

    @TableId
    private String id;

    private String userId;

    private String orderId;

    private BigDecimal freezeAmount;

    private Date createTime;

    private Date updateTime;

}
