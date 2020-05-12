2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/creational/factorymethod/Horse.java
package com.wz.creational.factorymethod;

/**
 * @author 隔壁老王
 * @create 2020-04-27 17:15
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体产品实现：马
public class Horse implements Animal {

    @Override
    public void show() {
        System.out.println("马在畜马厂中");
    }
}
