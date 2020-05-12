2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/facade/Logistics.java
package com.wz.structural.facade;

/**
 * @author 隔壁老王
 * @create 2020-04-29 19:21
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//物流系统
public class Logistics {
    public String shipGift(Gift gift){
        System.out.println(gift.getName()+" 进入物流系统");
        String orderID = "123456789";
        return orderID;
    }
}
