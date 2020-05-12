2
https://raw.githubusercontent.com/liuminchao123/JavaWeb_Learning/master/02.%20java/Java/%E9%BB%91%E9%A9%AC%E6%95%99%E7%A8%8B/13.%E3%80%90Collection%E3%80%81%E6%B3%9B%E5%9E%8B%E3%80%91-%E7%AC%94%E8%AE%B0/code/02_CollectionAndReflect/src/com/itheima/demo03/Generic/Demo04GenericInterface.java
package com.itheima.demo03.Generic;
/*
    测试含有泛型的接口
 */
public class Demo04GenericInterface {
    public static void main(String[] args) {
        //创建GenericInterfaceImpl1对象
        GenericInterfaceImpl1 gi1 = new GenericInterfaceImpl1();
        gi1.method("字符串");

        //创建GenericInterfaceImpl2对象
        GenericInterfaceImpl2<Integer> gi2 = new GenericInterfaceImpl2<>();
        gi2.method(10);

        GenericInterfaceImpl2<Double> gi3 = new GenericInterfaceImpl2<>();
        gi3.method(8.8);
    }
}
