2
https://raw.githubusercontent.com/liuminchao123/JavaWeb_Learning/master/02.%20java/Java/%E9%BB%91%E9%A9%AC%E6%95%99%E7%A8%8B/24.%E3%80%90Stream%E6%B5%81%E3%80%81%E6%96%B9%E6%B3%95%E5%BC%95%E7%94%A8%E3%80%91%E7%AC%94%E8%AE%B0/code/13_StreamAndMethodReference/src/com/itheima/demo06/StaticMethodReference/Demo01StaticMethodReference(1).java
package com.itheima.demo06.StaticMethodReference;

/*
    通过类名引用静态成员方法
    类已经存在,静态成员方法也已经存在
    就可以通过类名直接引用静态成员方法
 */
public class Demo01StaticMethodReference {
    //定义一个方法,方法的参数传递要计算绝对值的整数,和函数式接口Calcable
    public static int method(int number,Calcable c){
       return c.calsAbs(number);
    }

    public static void main(String[] args) {
        //调用method方法,传递计算绝对值得整数,和Lambda表达式
        int number = method(-10,(n)->{
            //对参数进行绝对值得计算并返回结果
            return Math.abs(n);
        });
        System.out.println(number);

        /*
            使用方法引用优化Lambda表达式
            Math类是存在的
            abs计算绝对值的静态方法也是已经存在的
            所以我们可以直接通过类名引用静态方法
         */
        int number2 = method(-10,Math::abs);
        System.out.println(number2);
    }
}
