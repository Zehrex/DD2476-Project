2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/strategy/JudgeRole.java
package com.wz.behavioral.strategy;

/**
 * @author 隔壁老王
 * @create 2020-05-05 18:54
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//用于传入不同的用户以判断其拥有的权限
public class JudgeRole {
    public String judge(Role role){
        RoleContext roleContext = new RoleContext(role);
        String execute = roleContext.execute();
        return execute;
    }
}
