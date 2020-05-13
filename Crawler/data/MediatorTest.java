2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/mediator/MediatorTest.java
package com.wz.behavioral.mediator;

/**
 * @author 隔壁老王
 * @create 2020-05-08 18:15
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//测试类
public class MediatorTest {
    public static void main(String[] args) {
        Mediator mediator = new MediatorImpl();
        Colleague colleague1 = new ColleagueImpl();
        mediator.register(colleague1);
        colleague1.send();
    }
}
