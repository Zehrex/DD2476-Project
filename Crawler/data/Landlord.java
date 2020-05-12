2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/proxy/staticproxy/Landlord.java
package com.wz.structural.proxy.staticproxy;

/**
 * @author 隔壁老王
 * @create 2020-05-04 15:48
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//真实角色：房东
public class Landlord implements Rent {

    @Override
    public void Renting() {
        System.out.println("房东租房");
    }
}
