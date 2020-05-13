2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/bridge/ABCBank.java
package com.wz.structural.bridge;

/**
 * @author 隔壁老王
 * @create 2020-05-03 16:26
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//扩展抽象化角色：中国农业银行
public class ABCBank extends Bank {

    public ABCBank(Account account) {
        super(account);
    }

    @Override
    Account showAccount() {
        System.out.println("打开中国农业银行账号");
        //调用实现化角色中的业务方法，这样当具体实现化角色业务改变时，扩展抽象化角色不用做任何改变，这样也符合迪米特法则
        account.openAccount();
        return account;
    }
}
