2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/creational/factorymethod/Cattle.java
package com.wz.creational.factorymethod;

/**
 * @author 隔壁老王
 * @create 2020-04-27 17:17
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体产品实现：牛
public class Cattle implements Animal {

    @Override
    public void show() {
        System.out.println("牛在畜牛厂中");
    }
}