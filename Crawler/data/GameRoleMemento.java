2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/memento/GameRoleMemento.java
package com.wz.behavioral.memento;

/**
 * @author 隔壁老王
 * @create 2020-05-08 7:11
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//不知道小伙伴们有没有玩过那种插卡的游戏机，记得有一款游戏叫做封神英杰传，是一款闯关游戏，可以存档。
//现将此游戏存档模拟成备忘录模式。
//备忘录角色：游戏角色类
public class GameRoleMemento {
    //当前游戏角色状态
    private String state;

    public GameRoleMemento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

}
