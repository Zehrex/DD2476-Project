2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/strategy/AdminRole.java
package com.wz.behavioral.strategy;

/**
 * @author 隔壁老王
 * @create 2020-05-05 18:47
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体策略类：系统管理员
public class AdminRole implements Role {

    private String name;

    public AdminRole(String name) {
        this.name = name;
    }

    @Override
    public String authority() {
        return name + "有最高权限";
    }
}
