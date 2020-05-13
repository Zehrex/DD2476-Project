2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/iterator/CollegeIteratorImpl.java
package com.wz.behavioral.iterator;

import java.util.List;

/**
 * @author 隔壁老王
 * @create 2020-05-05 16:35
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体迭代器角色
public class CollegeIteratorImpl implements  CollegeIterator {

    private List collegeList;
    private int index;
//    private College college;

    public CollegeIteratorImpl(List collegeList) {
        this.collegeList = collegeList;
    }

    @Override
    public College nextCollege() {
        System.out.println("index: "+index);
        College college = (College) collegeList.get(index);
        index++;
        return college;
    }

    @Override
    public boolean hasNextCollege() {
        if(index < collegeList.size()){
            return true;
        }
        return false;
    }
}
