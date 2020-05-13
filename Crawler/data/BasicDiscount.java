2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/decorate/business/BasicDiscount.java
package com.wz.structural.decorate.business;

/**
 * @author 隔壁老王
 * @create 2020-04-30 17:42
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//业务场景：隔壁老王的学习网站有vip用户、普通用户等，隔壁老王会在节假日会做一些促销活动。
//此业务场景为折扣处理业务。
//抽象构件角色：折扣处理
public abstract class BasicDiscount {
    public abstract void discount();
}
