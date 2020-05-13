2
https://raw.githubusercontent.com/liuminchao123/JavaWeb_Learning/master/02.%20java/Java/%E9%BB%91%E9%A9%AC%E6%95%99%E7%A8%8B/13.%E3%80%90Collection%E3%80%81%E6%B3%9B%E5%9E%8B%E3%80%91-%E7%AC%94%E8%AE%B0/code/02_CollectionAndReflect/src/com/itheima/demo03/Generic/Demo03GenericMethod.java
package com.itheima.demo03.Generic;
/*
    测试含有泛型的方法
 */
public class Demo03GenericMethod {
    public static void main(String[] args) {
        //创建GenericMethod对象
        GenericMethod gm = new GenericMethod();

        /*
            调用含有泛型的方法method01
            传递什么类型,泛型就是什么类型
         */
        gm.method01(10);
        gm.method01("abc");
        gm.method01(8.8);
        gm.method01(true);

        gm.method02("静态方法,不建议创建对象使用");

        //静态方法,通过类名.方法名(参数)可以直接使用
        GenericMethod.method02("静态方法");
        GenericMethod.method02(1);
    }
}
