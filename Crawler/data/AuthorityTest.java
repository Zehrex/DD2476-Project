2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/strategy/AuthorityTest.java
package com.wz.behavioral.strategy;

/**
 * @author 隔壁老王
 * @create 2020-05-05 18:52
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//客户端测试，此处只用于测试，没有连接数据库。
public class AuthorityTest {
    public static void main(String[] args) {
        JudgeRole judgeRole = new JudgeRole();
        String admin_role = judgeRole.judge(new AdminRole("admin_role"));
        System.out.println(admin_role);
        String order_role = judgeRole.judge(new OrderRole("order_role"));
        System.out.println(order_role);
    }
}
