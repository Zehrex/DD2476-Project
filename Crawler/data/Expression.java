2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/interpreter/Expression.java
package com.wz.behavioral.interpreter;

import java.util.HashMap;

/**
 * @author 隔壁老王
 * @create 2020-05-06 19:46
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//通过解释器模式实现四则运算
//抽象表达式角色：
public interface Expression {
    //通过HashMap键值对,可以获取到变量的值
    //a + b - c
    //解释公式和数值,key就是公式(表达式)的参数（a,b,c等）, value就是就是具体值
    int interpreter(HashMap<String, Integer> var);
}
