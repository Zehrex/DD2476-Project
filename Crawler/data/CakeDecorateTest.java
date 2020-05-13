2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/decorate/example/CakeDecorateTest.java
package com.wz.structural.decorate.example;

/**
 * @author 隔壁老王
 * @create 2020-04-30 18:28
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
public class CakeDecorateTest {
    public static void main(String[] args) {
        AbstractCake abstractCake = new Cake();
        abstractCake = new CreamDecorate(abstractCake);
        abstractCake = new StrawberryDecorate(abstractCake);

        System.out.println(abstractCake.description() + "，价格为：" + abstractCake.calculatePrice());
    }
}
