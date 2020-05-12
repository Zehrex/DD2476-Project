2
https://raw.githubusercontent.com/liuminchao123/JavaWeb_Learning/master/02.%20java/Java/%E9%BB%91%E9%A9%AC%E6%95%99%E7%A8%8B/12.%E3%80%90Object%E7%B1%BB%E3%80%81%E5%B8%B8%E7%94%A8API%E3%80%91-%E7%AC%94%E8%AE%B0/code/01_API/src/com/itheima/demo07Integer/Demo02Ineger.java
package com.itheima.demo07Integer;

import java.util.ArrayList;

/*
    自动装箱与自动拆箱:基本类型的数据和包装类之间可以自动的相互转换
    JDK1.5之后出现的新特性
 */
public class Demo02Ineger {
    public static void main(String[] args) {
        /*
            自动装箱:直接把int类型的整数赋值包装类
            Integer in = 1; 就相当于 Integer in = new Integer(1);
         */
        Integer in = 1;

        /*
            自动拆箱:in是包装类,无法直接参与运算,可以自动转换为基本数据类型,在进行计算
            in+2;就相当于 in.intVale() + 2 = 3
            in = in.intVale() + 2 = 3 又是一个自动装箱
         */
        in = in+2;

        ArrayList<Integer> list = new ArrayList<>();
        /*
            ArrayList集合无法直接存储整数,可以存储Integer包装类
         */
        list.add(1); //-->自动装箱 list.add(new Integer(1));

        int a = list.get(0); //-->自动拆箱  list.get(0).intValue();
    }
}
