2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/flyweight/Color.java
package com.wz.structural.flyweight;

/**
 * @author 隔壁老王
 * @create 2020-05-01 21:06
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//非享元角色：颜色类。
public class Color {
    private String color;

    public Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
