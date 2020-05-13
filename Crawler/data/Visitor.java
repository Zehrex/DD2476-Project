2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/visitor/Visitor.java
package com.wz.behavioral.visitor;

/**
 * @author 隔壁老王
 * @create 2020-05-09 20:42
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//抽象访问者：游客
public interface Visitor {
    void visit(Mural mural);
    void visit(JadeArticle jadeArticle);
}
