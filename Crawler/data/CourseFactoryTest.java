2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/creational/abstractfactory/CourseFactoryTest.java
package com.wz.creational.abstractfactory;

/**
 * @author 隔壁老王
 * @create 2020-04-28 16:58
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
public class CourseFactoryTest {
    public static void main(String[] args) {
        //相当于客户端，此时无需关心视频、笔记等，只需要关心从对应工厂拿对应产品即可
        //从同一工厂拿出来的东西属于同一产品族
        JavaCourseFactory javaCourseFactory = new JavaCourseFactory();
        Video javaVideo = javaCourseFactory.createVideo();
        javaVideo.show();
        Note javaNote = javaCourseFactory.createNote();
        javaNote.show();
    }
}
