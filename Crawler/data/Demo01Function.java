2
https://raw.githubusercontent.com/liuminchao123/JavaWeb_Learning/master/02.%20java/Java/%E9%BB%91%E9%A9%AC%E6%95%99%E7%A8%8B/23.%E3%80%90%E5%87%BD%E6%95%B0%E5%BC%8F%E6%8E%A5%E5%8F%A3%E3%80%91-%E7%AC%94%E8%AE%B0/code/12_FunctionalInterface/src/com/itheima/demo07/Function/Demo01Function.java
package com.itheima.demo07.Function;

import java.util.function.Function;

/*
    java.util.function.Function<T,R>接口用来根据一个类型的数据得到另一个类型的数据，
        前者称为前置条件，后者称为后置条件。
    Function接口中最主要的抽象方法为：R apply(T t)，根据类型T的参数获取类型R的结果。
        使用的场景例如：将String类型转换为Integer类型。
 */
public class Demo01Function {
    /*
        定义一个方法
        方法的参数传递一个字符串类型的整数
        方法的参数传递一个Function接口,泛型使用<String,Integer>
        使用Function接口中的方法apply,把字符串类型的整数,转换为Integer类型的整数
     */
    public static void change(String s, Function<String,Integer> fun){
        //Integer in = fun.apply(s);
        int in = fun.apply(s);//自动拆箱 Integer->int
        System.out.println(in);
    }

    public static void main(String[] args) {
        //定义一个字符串类型的整数
        String s = "1234";
        //调用change方法,传递字符串类型的整数,和Lambda表达式
        change(s,(String str)->{
            //把字符串类型的整数,转换为Integer类型的整数返回
            return Integer.parseInt(str);
        });
        //优化Lambda
        change(s,str->Integer.parseInt(str));
    }
}
