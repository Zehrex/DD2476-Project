2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/state/VideoStateTest.java
package com.wz.behavioral.state;

/**
 * @author 隔壁老王
 * @create 2020-05-10 15:57
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//测试类
public class VideoStateTest {
    public static void main(String[] args) {
        VideoContext videoContext = new VideoContext();
        videoContext.setVideoState(new VideoPlayState());

        System.out.println("当前状态：" + videoContext.getVideoState().getClass().getSimpleName());

        videoContext.pause();
        System.out.println("当前状态：" + videoContext.getVideoState().getClass().getSimpleName());
        videoContext.speed();
        System.out.println("当前状态：" + videoContext.getVideoState().getClass().getSimpleName());
        videoContext.close();
        System.out.println("当前状态：" + videoContext.getVideoState().getClass().getSimpleName());

        videoContext.speed();
    }
}
