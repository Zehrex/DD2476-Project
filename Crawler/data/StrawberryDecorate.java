2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/decorate/example/StrawberryDecorate.java
package com.wz.structural.decorate.example;

/**
 * @author 隔壁老王
 * @create 2020-04-30 18:09
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体装饰类：草莓装饰类
public class StrawberryDecorate extends AbstractDecorate {

    public StrawberryDecorate(AbstractCake abstractCake) {
        super(abstractCake);
    }

    @Override
    public String description() {
        return super.description() + " 加上草莓";
    }

    @Override
    public int calculatePrice() {
        return super.calculatePrice() + 10;
    }
}
