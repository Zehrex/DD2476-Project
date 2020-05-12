2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/visitor/VisitorTest.java
package com.wz.behavioral.visitor;

/**
 * @author 隔壁老王
 * @create 2020-05-09 22:51
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//测试类
public class VisitorTest {
    public static void main(String[] args) {
        MuseumObjectStructure museumObjectStructure = new MuseumObjectStructure();

        Mural mural = new Mural();
        mural.setName("壁画");
        JadeArticle jadeArticle = new JadeArticle();
        jadeArticle.setName("玉器");

        museumObjectStructure.add(mural);
        museumObjectStructure.add(jadeArticle);

        Visitor chineseVisitor=new ChineseVisitor();
        museumObjectStructure.accept(chineseVisitor);
        System.out.println("------------------------");
        Visitor foreignVisitor = new ForeignVisitor();
        museumObjectStructure.accept(foreignVisitor);
    }
}
