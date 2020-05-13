6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/pay/entity/PayNotifyRecord.java
package com.github.taoroot.taoiot.pay.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@ApiModel("异步通知记录")
@Data
@TableName("tb_pay_notify_record")
@EqualsAndHashCode(callSuper = true)
public class PayNotifyRecord extends Model<PayNotifyRecord> {
    private static final long serialVersionUID = 1L;

    @TableId
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "响应ID")
    private String notifyId;

    @ApiModelProperty(value = "请求报文")
    private String request;

    @ApiModelProperty(value = "响应报文")
    private String response;

    @ApiModelProperty(value = "系统订单号")
    private String orderNo;

    @ApiModelProperty(value = "http状态")
    private String httpStatus;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
