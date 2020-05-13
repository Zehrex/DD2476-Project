2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/composite/transparent/VideoFile.java
package com.wz.structural.composite.transparent;

/**
 * @author 隔壁老王
 * @create 2020-05-03 11:47
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//树叶构件角色：视频文件类
public class VideoFile extends AbstractFile {

    private String name;

    public VideoFile(String name) {
        this.name = name;
    }

    @Override
    public void add(AbstractFile file) {
        throw new UnsupportedOperationException("不支持该操作");
    }

    @Override
    public void remove(AbstractFile file) {
        throw new UnsupportedOperationException("不支持该操作");
    }

    @Override
    public AbstractFile getChild(int i) {
        throw new UnsupportedOperationException("不支持该操作");
    }

    @Override
    public void killVirus() {
        System.out.println("**对视频文件" + name + "进行杀毒");
    }
}
