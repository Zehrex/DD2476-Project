2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/observer/ObserverTest.java
package com.wz.behavioral.observer;

/**
 * @author 隔壁老王
 * @create 2020-05-07 18:22
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//测试类
public class ObserverTest {
    public static void main(String[] args) {
        WeChatSubscription weChatSubscription = new WeChatSubscription();

        Observer Tom = new User("Tom");
        Observer Jack = new User("Jack");
        Observer Mary = new User("Mary");

        weChatSubscription.addObserver(Tom);
        weChatSubscription.addObserver(Jack);
        weChatSubscription.addObserver(Mary);

        weChatSubscription.setMessage("源码系列之从源码看ArrayList");
        weChatSubscription.notifyObserver();

        System.out.println("*****************************************");

        //取消关注
        weChatSubscription.delObserver(Jack);

        weChatSubscription.setMessage("源码系列之从源码看HashMap");
        weChatSubscription.notifyObserver();
    }
}
