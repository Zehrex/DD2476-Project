2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/flyweight/BlockImpl.java
package com.wz.structural.flyweight;

/**
 * @author 隔壁老王
 * @create 2020-05-01 20:26
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体享元角色：创建那种形状的方块的类
public class BlockImpl implements Block {
    //这种写法为外部状态，shape随外界环境改变而改变。若有直接赋值的属性，则为内部状态
    private String shape;

    public BlockImpl(String shape) {
        this.shape = shape;
    }

    @Override
    public void display(Color color) {
        System.out.println("这是" + shape);
        System.out.println(shape + "的颜色为：" + color.getColor());
    }
}

