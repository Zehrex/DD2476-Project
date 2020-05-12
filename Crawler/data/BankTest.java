2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/bridge/BankTest.java
package com.wz.structural.bridge;

/**
 * @author 隔壁老王
 * @create 2020-05-03 16:52
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//测试类
public class BankTest {
    public static void main(String[] args) {
        Bank icbcBank = new ICBCBank(new DepositAccount());
        Account icbcAccount = icbcBank.showAccount();
        icbcAccount.showAccountType();

        Bank icbcBank2 = new ICBCBank(new CurrentAccount());
        Account icbcAccount2 = icbcBank2.showAccount();
        icbcAccount2.showAccountType();

        Bank abcBank = new ABCBank(new CurrentAccount());
        Account abcAccount = abcBank.showAccount();
        abcAccount.showAccountType();
    }
}
