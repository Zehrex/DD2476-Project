2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/strategy/OrderRole.java
package com.wz.behavioral.strategy;

/**
 * @author 隔壁老王
 * @create 2020-05-05 18:50
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体策略类：订单管理员
public class OrderRole implements Role {
    private String name;

    public OrderRole(String name) {
        this.name = name;
    }

    @Override
    public String authority() {
        return name + "有操作订单的权限";
    }
}
