2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/memento/MementoTest.java
package com.wz.behavioral.memento;

/**
 * @author 隔壁老王
 * @create 2020-05-08 7:46
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//测试类
public class MementoTest {
    public static void main(String[] args) {
        GameFileOriginator gameFileOriginator = new GameFileOriginator();
        GameCaretaker gameCaretaker = new GameCaretaker();
        //设置游戏的状态并创建备忘录角色
        gameFileOriginator.setState("已通过第一关");
        gameCaretaker.setGameRoleMemento(gameFileOriginator.createGameRoleMemento());
        //获取现在的状态
        System.out.println(gameFileOriginator.getState());

        //设置新的状态
        gameFileOriginator.setState("通过第二关失败");
        //获取现在的状态
        System.out.println(gameFileOriginator.getState());
        //通过其他关卡失败,读取以前的存档
        gameFileOriginator.recoverGameRoleMemento(gameCaretaker.getGameRoleMemento());
        System.out.println(gameFileOriginator.getState());
    }
}
