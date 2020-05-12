2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/bridge/Bank.java
package com.wz.structural.bridge;

/**
 * @author 隔壁老王
 * @create 2020-05-03 16:19
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//抽象化角色：银行
public abstract class Bank {
    protected Account account;

    public Bank(Account account) {
        this.account = account;
    }

    abstract Account showAccount();
}
