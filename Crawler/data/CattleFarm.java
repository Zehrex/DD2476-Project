2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/creational/factorymethod/CattleFarm.java
package com.wz.creational.factorymethod;

/**
 * @author 隔壁老王
 * @create 2020-04-27 17:57
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//工厂实现：畜牛厂
public class CattleFarm implements AnimalFarm {
    @Override
    public Animal newAnimal() {
        System.out.println("新牛出生");
        return new Cattle();
    }
}
