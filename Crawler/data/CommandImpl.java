2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/command/CommandImpl.java
package com.wz.behavioral.command;

/**
 * @author 隔壁老王
 * @create 2020-05-08 14:53
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体命令角色
public class CommandImpl implements Command {

    private Employee employee;

    public CommandImpl(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void execute() {
        employee.action();
    }
}
