2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/decorate/business/DiscountDecorateTest.java
package com.wz.structural.decorate.business;

/**
 * @author 隔壁老王
 * @create 2020-04-30 19:00
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
public class DiscountDecorateTest {
    public static void main(String[] args) {
        BasicDiscount basicDiscount;
        basicDiscount = new VIPDiscount();
        VIPDiscountDecorate vipDiscountDecorate;
        vipDiscountDecorate = new VIPDiscountDecorate(basicDiscount);
        vipDiscountDecorate.discount();
        vipDiscountDecorate.expandBusiness();
    }
}
