2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/visitor/Museum.java
package com.wz.behavioral.visitor;

/**
 * @author 隔壁老王
 * @create 2020-05-09 20:34
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//业务场景：游客访问博物馆
//抽象元素角色：博物馆
public interface Museum {
    void accept(Visitor visitor);
}
