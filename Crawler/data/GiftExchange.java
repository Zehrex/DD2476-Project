2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/facade/GiftExchange.java
package com.wz.structural.facade;

/**
 * @author 隔壁老王
 * @create 2020-04-29 19:30
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//外观类
public class GiftExchange {
    private Check check = new Check();
    private PointPayment pointPayment = new PointPayment();
    private Logistics logistics = new Logistics();

    public void giftExchange(Gift gift){
        //校验通过
        if(check.isAvailable(gift)){
            //积分支付成功
            if (pointPayment.pay(gift)){
                //生成物流订单
                String s = logistics.shipGift(gift);
                System.out.println("您的礼物已发货，订单号为"+s);
            }
        }
    }
}
