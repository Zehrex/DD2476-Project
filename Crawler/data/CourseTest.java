2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/templatemethod/CourseTest.java
package com.wz.behavioral.templatemethod;

/**
 * @author 隔壁老王
 * @create 2020-05-04 20:34
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//测试类
public class CourseTest {
    public static void main(String[] args) {
        System.out.println("制作后端课程");
        AbstractCourse javaCourse = new JavaCourse();
        javaCourse.makeCourse();

        System.out.println("-----------------");

        System.out.println("制作前端课程");
        AbstractCourse frontEndCourse = new FrontEndCourse(true);
        frontEndCourse.makeCourse();

    }
}
