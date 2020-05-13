2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/decorate/business/VIPDiscountDecorate.java
package com.wz.structural.decorate.business;

/**
 * @author 隔壁老王
 * @create 2020-04-30 17:55
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
public class VIPDiscountDecorate extends AbstractDiscountDecorate {

    public VIPDiscountDecorate(BasicDiscount basicDiscount) {
        super(basicDiscount);
    }

    @Override
    public void discount() {
        super.discount();
    }

    @Override
    public void expandBusiness() {
        System.out.println("VIP用户折扣扩展业务");
    }

}
