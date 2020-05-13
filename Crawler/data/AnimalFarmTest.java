2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/creational/factorymethod/AnimalFarmTest.java
package com.wz.creational.factorymethod;

/**
 * @author 隔壁老王
 * @create 2020-04-27 17:22
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//客户端、测试类
public class AnimalFarmTest {
    public static void main(String[] args) {
        HorseFarm horseFarm = new HorseFarm();
        Animal horse = horseFarm.newAnimal();
        horse.show();

        CattleFarm cattleFarm = new CattleFarm();
        Animal cattle = cattleFarm.newAnimal();
        cattle.show();
    }
}
