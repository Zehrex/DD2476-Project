2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/observer/WeChatSubscription.java
package com.wz.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 隔壁老王
 * @create 2020-05-07 18:01
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体被观察者：微信公众号
public class WeChatSubscription implements  Observerable {

    private List<Observer> list = new ArrayList<>();
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void addObserver(Observer o) {
        list.add(o);
    }

    @Override
    public void delObserver(Observer o) {
        if (!list.isEmpty()) {
            list.remove(o);
        }
    }

    @Override
    public void notifyObserver() {
        for(int i = 0; i < list.size(); i++) {
            Observer observer = list.get(i);
            observer.update(message);
        }
    }
}
