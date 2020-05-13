2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/mediator/Colleague.java
package com.wz.behavioral.mediator;

/**
 * @author 隔壁老王
 * @create 2020-05-08 18:02
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//抽象同事类
public abstract class Colleague {
    //中介者抽象类
    protected Mediator mediator;

    //接受消息
    public abstract void receive();

    //发送消息
    public abstract void send();

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
}
