2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/visitor/JadeArticle.java
package com.wz.behavioral.visitor;

/**
 * @author 隔壁老王
 * @create 2020-05-09 21:48
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体元素：玉器
public class JadeArticle implements Museum {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
