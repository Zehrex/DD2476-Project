2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/templatemethod/FrontEndCourse.java
package com.wz.behavioral.templatemethod;

/**
 * @author 隔壁老王
 * @create 2020-05-04 20:28
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体子类：前端课程类
public class FrontEndCourse extends AbstractCourse {

    //将是否编写笔记交给客户端（在这里指测试类）
    private boolean flag = false;

    public FrontEndCourse(boolean flag) {
        this.flag = flag;
    }

    @Override
    void packageCourse() {
        System.out.println("提供前端课程代码");
        System.out.println("提供前端课程所需的图片等资料");
    }

    @Override
    protected boolean isWriteNote() {
        return this.flag;
    }
}
