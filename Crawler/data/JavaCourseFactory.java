2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/creational/abstractfactory/JavaCourseFactory.java
package com.wz.creational.abstractfactory;

/**
 * @author 隔壁老王
 * @create 2020-04-28 16:38
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
public class JavaCourseFactory implements CourseFactory {

    @Override
    public Video createVideo() {
        System.out.println("录制Java课程视频");
        return new JavaVideo();
    }

    @Override
    public Note createNote() {
        System.out.println("编写Java课程笔记");
        return new JavaNote();
    }
}
