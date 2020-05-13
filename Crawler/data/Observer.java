2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/observer/Observer.java
package com.wz.behavioral.observer;

/**
 * @author 隔壁老王
 * @create 2020-05-07 17:54
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//抽象观察者角色：当接到具体主题的更改通知时被调用。
//老王在线提醒，这是自己定义的Observer接口，不是JDK中的。其他类使用时注意别导错包
public interface Observer {
    void update(String message);
}
