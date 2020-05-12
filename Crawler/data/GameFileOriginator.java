2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/memento/GameFileOriginator.java
package com.wz.behavioral.memento;

/**
 * @author 隔壁老王
 * @create 2020-05-08 7:38
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//游戏存档发起人角色：
public class GameFileOriginator {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    //创建备忘录角色
    public GameRoleMemento createGameRoleMemento() {
        return new GameRoleMemento(state);
    }

    //恢复备忘录角色
    public void recoverGameRoleMemento(GameRoleMemento gameRoleMemento){
        this.state = gameRoleMemento.getState();
    }

}
