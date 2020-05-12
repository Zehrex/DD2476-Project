2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/creational/factorymethod/HorseFarm.java
package com.wz.creational.factorymethod;

/**
 * @author 隔壁老王
 * @create 2020-04-27 17:19
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//工厂实现：畜马厂
public class HorseFarm implements AnimalFarm {
    @Override
    public Animal newAnimal() {
        System.out.println("新马出生");
        return new Horse();
    }
}
