2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/iterator/CollegeTest.java
package com.wz.behavioral.iterator;

/**
 * @author 隔壁老王
 * @create 2020-05-05 16:47
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//测试类
public class CollegeTest {
    public static void main(String[] args) {
        College college1 = new College("建筑学院");
        College college2 = new College("经济管理学院");
        College college3 = new College("土木水利学院");
        College college4 = new College("公共管理学院");
        College college5 = new College("环境学院");
        College college6 = new College("马克思主义学院");

        CollegeAggregate collegeAggregate = new CollegeAggregateImpl();
        collegeAggregate.addCollege(college1);
        collegeAggregate.addCollege(college2);
        collegeAggregate.addCollege(college3);
        collegeAggregate.addCollege(college4);
        collegeAggregate.addCollege(college5);
        collegeAggregate.addCollege(college6);

        System.out.println("-----学院列表----");
        printCollege(collegeAggregate);
        collegeAggregate.removeCollege(college2);
        collegeAggregate.removeCollege(college5);

        System.out.println("-----删除后的学院列表----");
        printCollege(collegeAggregate);
    }

    public static void printCollege(CollegeAggregate collegeAggregate){
        CollegeIterator iterator = collegeAggregate.getIterator();
        while (iterator.hasNextCollege()){
            College college = iterator.nextCollege();
            System.out.println(college.getName());
        }
    }
}
