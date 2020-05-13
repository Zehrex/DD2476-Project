2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/creational/abstractfactory/CourseFactory.java
package com.wz.creational.abstractfactory;

/**
 * @author 隔壁老王
 * @create 2020-04-28 16:22
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//引入业务场景：隔壁老王的团队录制了一套Java视频，应广大粉丝要求，又录制了一套Python视频。
//此时就可以使用工厂方法模式。
//但现在粉丝有不满意了，想要Java视频和Python视频的笔记。
//此时如果还使用工厂方法模式可想而知，需要创建许多许多的类。
//抽象工厂应运而生。
public interface CourseFactory {
    Video createVideo();
    Note createNote();
}
