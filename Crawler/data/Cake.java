2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/decorate/example/Cake.java
package com.wz.structural.decorate.example;

/**
 * @author 隔壁老王
 * @create 2020-04-30 18:05
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体构建角色（被装饰类）：蛋糕
public class Cake extends AbstractCake {

    @Override
    public String description() {
        return "蛋糕";
    }

    @Override
    public int calculatePrice() {
        return 15;
    }

}
