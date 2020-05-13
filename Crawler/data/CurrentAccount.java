2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/bridge/CurrentAccount.java
package com.wz.structural.bridge;

/**
 * @author 隔壁老王
 * @create 2020-05-03 16:18
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体实现化角色：活期存款的账户
public class CurrentAccount implements Account {

    @Override
    public Account openAccount() {
        System.out.println("打开一个活期存款的账户");
        return new CurrentAccount();
    }

    @Override
    public void showAccountType() {
        System.out.println("这是一个活期账号");
    }
}
