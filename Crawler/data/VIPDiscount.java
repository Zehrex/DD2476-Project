2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/decorate/business/VIPDiscount.java
package com.wz.structural.decorate.business;

/**
 * @author 隔壁老王
 * @create 2020-04-30 17:47
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体构件角色（被装饰角色）：VIP折扣处理业务
public class VIPDiscount extends BasicDiscount {
    @Override
    public void discount() {
        System.out.println("处理VIP用户折扣的业务");
    }
}
