2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/creational/builder/ComputerBuilderTest.java
package com.wz.creational.builder;

/**
 * @author 隔壁老王
 * @create 2020-04-28 19:34
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
public class ComputerBuilderTest {
    public static void main(String[] args) {
        ComputerBuilder computerActualBuilder = new ComputerActualBuilder();
        Conductor conductor = new Conductor();
        conductor.setComputerBuilder(computerActualBuilder);
        Computer computer = conductor.AssembleComputer("Intel i7-9700K", "华硕 PRIME X570-P 主板",
                "金士顿(Kingston) DDR4 2666 32GB", " Manli万丽GTX1660 Super 6G涡轮电竞游戏独立显卡",
                "AOC 23.8英寸 AH-IPS硬屏 1.5mm窄边框 低蓝光爱眼不闪屏 电脑显示器");
        System.out.println(computer);
    }
}
