2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/decorate/example/AbstractCake.java
package com.wz.structural.decorate.example;

/**
 * @author 隔壁老王
 * @create 2020-04-30 18:03
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//抽象构件角色：抽象蛋糕
public abstract class AbstractCake {

    //描述
    public abstract String description();

    //计算价格
    public abstract int calculatePrice();

}
