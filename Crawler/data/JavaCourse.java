2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/templatemethod/JavaCourse.java
package com.wz.behavioral.templatemethod;

/**
 * @author 隔壁老王
 * @create 2020-05-04 20:27
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体子类：Java课程类
public class JavaCourse extends AbstractCourse {

    //java课程需要提供笔记
    @Override
    protected boolean isWriteNote() {
        return true;
    }

    @Override
    void packageCourse() {
        System.out.println("提供Java课程的源代码");
    }
}
