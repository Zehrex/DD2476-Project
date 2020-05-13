2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/composite/transparent/Folder.java
package com.wz.structural.composite.transparent;

import java.util.ArrayList;

/**
 * @author 隔壁老王
 * @create 2020-05-03 12:02
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//树枝构件角色：文件夹类
public class Folder extends AbstractFile {

    private String name;

    //定义集合fileList，用于存储AbstractFile类型的成员
    private ArrayList<AbstractFile> fileList = new ArrayList<AbstractFile>();

    public Folder(String name) {
        this.name = name;
    }

    @Override
    public void add(AbstractFile file) {
        fileList.add(file);
    }

    @Override
    public void remove(AbstractFile file) {
        fileList.remove(file);
    }

    @Override
    public AbstractFile getChild(int i) {
        return fileList.get(i);
    }

    @Override
    public void killVirus() {
        System.out.println("--对文件夹" + name + "进行杀毒");
        //递归调用成员构件的killVirus()方法
        for(Object obj : fileList) {
            ((AbstractFile)obj).killVirus();
        }
    }
}
