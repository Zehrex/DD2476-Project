2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/iterator/CollegeAggregate.java
package com.wz.behavioral.iterator;

/**
 * @author 隔壁老王
 * @create 2020-05-05 16:25
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//清华大学设有20学院，对其进行迭代。
//抽象聚合角色
public interface CollegeAggregate {
    void addCollege(College college);
    void removeCollege(College college);
    CollegeIterator getIterator();
}
