2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/flyweight/Block.java
package com.wz.structural.flyweight;

/**
 * @author 隔壁老王
 * @create 2020-05-01 20:20
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//业务场景：俄罗斯方块相信很多小伙伴都应该玩过，没有玩过的可以自行百度。
//里面的方块有很多种形状，如果没出现一个方块，就要新创建一个对象，那岂不是不是很浪费内存，
//同时又浪费手机性能。怎么解决呢。这时候就需要享元模式了。
//抽象享元角色：方块
public interface Block {
    //非享元的外部状态以参数的形式通过方法传入。
    void display(Color color);
}
