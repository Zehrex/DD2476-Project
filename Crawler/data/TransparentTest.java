2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/composite/transparent/TransparentTest.java
package com.wz.structural.composite.transparent;

/**
 * @author 隔壁老王
 * @create 2020-05-03 12:07
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
public class TransparentTest {
    public static void main(String[] args) {
        //针对抽象构件编程
        AbstractFile file1,file2,file3,file4,file5,folder1,folder2,folder3;

        folder1 = new Folder("隔壁老王的课程资料");
        folder2 = new Folder("音频文件");
        folder3 = new Folder("视频文件");

        file1 = new VideoFile("走进计算机世界.mp4");
        file2 = new VideoFile("认识Java.mp4");
        file3 = new VideoFile("了解Java数据类型.mp4");

        file4 = new AudioFile("为什么要学习软件工程.mp3");
        file5 = new AudioFile("怎样学好软件工程.mp3");

        folder1.add(folder2);
        folder1.add(folder3);

        folder2.add(file4);
        folder2.add(file5);

        folder3.add(file1);
        folder3.add(file2);
        folder3.add(file3);

        //从“隔壁老王的课程资料”节点开始进行杀毒操作
        folder1.killVirus();
    }
}
