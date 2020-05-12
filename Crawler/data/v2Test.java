2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/creational/builder/v2/v2Test.java
package com.wz.creational.builder.v2;

/**
 * @author 隔壁老王
 * @create 2020-04-28 20:08
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
public class v2Test {
    public static void main(String[] args) {
        Computer computer = new Computer.ComputerBuilder().buildCPU("Intel i7-9700K").buildMotherboard("华硕 PRIME X570-P 主板")
                .buildRAM("金士顿(Kingston) DDR4 2666 32GB").buildGPU(" Manli万丽GTX1660 Super 6G涡轮电竞游戏独立显卡")
                .buildMonitor("AOC 23.8英寸 AH-IPS硬屏 1.5mm窄边框 低蓝光爱眼不闪屏 电脑显示器").build();
        System.out.println(computer);
    }
}
