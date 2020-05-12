2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/observer/Observerable.java
package com.wz.behavioral.observer;

/**
 * @author 隔壁老王
 * @create 2020-05-07 17:52
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//业务场景：隔壁老王有自己的微信公众号，此公众号会发布文章。公众号即被观察者，关注公众号的用户即观察者。
//抽象被观察者：声明了添加、删除、通知所有观察者的方法
public interface Observerable {
    void addObserver(Observer o);
    void delObserver(Observer o);
    void notifyObserver();
}
