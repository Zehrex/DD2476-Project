2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/chainofresponsibility/Course.java
package com.wz.behavioral.chainofresponsibility;

/**
 * @author 隔壁老王
 * @create 2020-05-08 19:56
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//业务场景：隔壁老王的在线教育网站上线一门课程需要经过审批，视频审批、笔记审批等
//课程类
public class Course {
    private String name;
    private String note;
    private String video;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", video='" + video + '\'' +
                '}';
    }
}
