2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/adapter/objectadapter/AdapterTest.java
package com.wz.structural.adapter.objectadapter;

/**
 * @author 隔壁老王
 * @create 2020-05-01 15:45
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//测试类
public class AdapterTest {
    public static void main(String[] args) {
        Input input = new Input();
        Output Output = new Adapter(input);
        Output.output();
    }
}
