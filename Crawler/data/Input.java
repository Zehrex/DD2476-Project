2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/adapter/objectadapter/Input.java
package com.wz.structural.adapter.objectadapter;

/**
 * @author 隔壁老王
 * @create 2020-05-01 15:44
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//引入场景：隔壁老王的笔记本充电应该是20v、3.25A，而充电插排是220v，此时就需要一个适配类来进行转化。
//适配者类：输入220v电流
public class Input {
    public void input(){
        System.out.println("输入220v电流");
    }
}
