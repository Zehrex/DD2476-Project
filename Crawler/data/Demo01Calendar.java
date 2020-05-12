2
https://raw.githubusercontent.com/liuminchao123/JavaWeb_Learning/master/02.%20java/Java/%E9%BB%91%E9%A9%AC%E6%95%99%E7%A8%8B/12.%E3%80%90Object%E7%B1%BB%E3%80%81%E5%B8%B8%E7%94%A8API%E3%80%91-%E7%AC%94%E8%AE%B0/code/01_API/src/com/itheima/demo04/Calendar/Demo01Calendar.java
package com.itheima.demo04.Calendar;

import java.util.Calendar;

/*
    java.util.Calendar类:日历类
    Calendar类是一个抽象类,里边提供了很多操作日历字段的方法(YEAR、MONTH、DAY_OF_MONTH、HOUR )
    Calendar类无法直接创建对象使用,里边有一个静态方法叫getInstance(),该方法返回了Calendar类的子类对象
    static Calendar getInstance() 使用默认时区和语言环境获得一个日历。
 */
public class Demo01Calendar {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();//多态
        System.out.println(c);
    }

}
