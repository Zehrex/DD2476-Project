2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/templatemethod/AbstractCourse.java
package com.wz.behavioral.templatemethod;

/**
 * @author 隔壁老王
 * @create 2020-05-04 20:17
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//业务场景：隔壁老王的网站上线新课程前要制作课程，包括制作视频、PPT、笔记
//抽象类：抽象课程类
public abstract class AbstractCourse {
    //模板方法
    protected final void makeCourse(){
        this.makePPT();
        this.makeVideo();
        if(isWriteNote()){
            this.writeNote();
        }
        this.packageCourse();
    }

    //具体方法
    final void makeVideo(){
        System.out.println("制作视频");
    }
    final void makePPT(){
        System.out.println("制作PPT");
    }
    final void writeNote(){
        System.out.println("编写笔记");
    }

    //钩子方法：是否需要编写笔记
    protected boolean isWriteNote(){
        return false;
    }

    //抽象方法：打包课程
    abstract void packageCourse();
}
