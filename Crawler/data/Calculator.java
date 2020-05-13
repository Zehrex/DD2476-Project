2
https://raw.githubusercontent.com/liuminchao123/JavaWeb_Learning/master/02.%20java/Java/%E9%BB%91%E9%A9%AC%E6%95%99%E7%A8%8B/18.%E3%80%90%E7%BA%BF%E7%A8%8B%E6%B1%A0%E3%80%81Lambda%E8%A1%A8%E8%BE%BE%E5%BC%8F%E3%80%91-%E7%AC%94%E8%AE%B0/code/07_ThreadAndLambda/src/com/itheima/demo06/Lambda/Calculator.java
package com.itheima.demo06.Lambda;
/*
    给定一个计算器Calculator接口，内含抽象方法calc可以将两个int数字相加得到和值
 */
public interface Calculator {
    //定义一个计算两个int整数和的方法并返回结果
    public abstract int calc(int a,int b);
}
