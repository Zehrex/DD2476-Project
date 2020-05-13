2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/iterator/CollegeAggregateImpl.java
package com.wz.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 隔壁老王
 * @create 2020-05-05 16:31
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体聚合角色
public class CollegeAggregateImpl implements CollegeAggregate {

    private List collegeList;

    public CollegeAggregateImpl() {
        this.collegeList = new ArrayList();
    }

    @Override
    public void addCollege(College college) {
        collegeList.add(college);
    }

    @Override
    public void removeCollege(College college) {
        collegeList.remove(college);
    }

    @Override
    public CollegeIterator getIterator() {
        return new CollegeIteratorImpl(collegeList);
    }
}
