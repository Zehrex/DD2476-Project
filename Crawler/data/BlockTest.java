2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/flyweight/BlockTest.java
package com.wz.structural.flyweight;

/**
 * @author 隔壁老王
 * @create 2020-05-01 20:37
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//测试类
public class BlockTest {
    public static void main(String[] args) {
        Block block1 = BlockFactory.getBlock("田字形方块");
        block1.display(new Color("black"));
        Block block2 = BlockFactory.getBlock("L型方块");
        block2.display(new Color("red"));
        Block block3 = BlockFactory.getBlock("田字形方块");
        block3.display(new Color("blue"));
        Block block4 = BlockFactory.getBlock("L型方块");
        block3.display(new Color("blue"));
        //对比一下内存地址
        System.out.println(block1 == block3);
        System.out.println(block2 == block4);
    }
}
