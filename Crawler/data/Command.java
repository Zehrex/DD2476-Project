2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/command/Command.java
package com.wz.behavioral.command;

/**
 * @author 隔壁老王
 * @create 2020-05-08 14:34
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//业务场景：公司Boss下达命令做一个项目，员工接收Boss下的的命令。此场景中，Boss请求者。员工是接收者。
//抽象命令类
public interface Command {
    void execute();
}
