2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/facade/PointPayment.java
package com.wz.structural.facade;

/**
 * @author 隔壁老王
 * @create 2020-04-29 19:18
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//积分支付
public class PointPayment {
    public boolean pay(Gift gift){
        System.out.println("支付 "+gift.getName()+" 的积分成功");
        return true;
    }
}
