6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/pay/entity/PayTradeOrder.java
package com.github.taoroot.taoiot.pay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@ApiModel("支付订单")
@Data
@TableName("tb_pay_trade_order")
@EqualsAndHashCode(callSuper = true)
public class PayTradeOrder extends Model<PayTradeOrder> {
    private static final long serialVersionUID = 1L;

    // 交易信息
    @ApiModelProperty(value = "支付订单号")
    @TableId(type = IdType.AUTO)
    private String orderId;

    @ApiModelProperty(value = "支付状态,0-订单生成,1-支付中,2-支付成功,3-业务处理完成")
    private String orderStatus;

    @ApiModelProperty(value = "订单标题")
    private String orderSubject;

    @ApiModelProperty(value = "订单价格")
    private Integer orderAmount;

    @ApiModelProperty(value = "第三方支付成功时间")
    private String orderPaySuccessTime;

    // 第三方支付信息
    @ApiModelProperty(value = "通道商户号")
    private String channelMchNo;

    @ApiModelProperty(value = "支付类型")
    private String channelType;

    @ApiModelProperty(value = "第三方支付订单号")
    private String channelOrderNo;

    @ApiModelProperty(value = "第三方支付错误码")
    private String channelErrCode;

    @ApiModelProperty(value = "第三方支付错误描述")
    private String channelErrMsg;

    // 用户信息
    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    // 用户信息
    @ApiModelProperty(value = "OpenID，由第三方支付提供")
    private String openId;

    @ApiModelProperty(value = "用户IP")
    private String userIp;


    // 商品信息
    @ApiModelProperty(value = "商品id")
    private Integer goodsId;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品副标题")
    private String goodsSubtitle;

    @ApiModelProperty(value = "商品成本,单位分")
    private Integer goodsPrice;

    @ApiModelProperty(value = "商品零售价,单位分")
    private Integer goodsRetailPrice;

    @ApiModelProperty(value = "商品优惠价,单位分")
    private Integer goodsOfferPrice;

    @ApiModelProperty(value = "产品主图,url相对地址")
    private String goodsMainImage;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
