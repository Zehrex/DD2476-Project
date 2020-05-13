2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/state/VideoCloseState.java
package com.wz.behavioral.state;

/**
 * @author 隔壁老王
 * @create 2020-05-10 15:29
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体状态角色：关闭状态
public class VideoCloseState extends VideoState {
    @Override
    public void play() {
        super.videoContext.setVideoState(VideoContext.PLAY_STATE);
    }

    @Override
    public void pause() {
        System.out.println("Error，视频正处在关闭状态，不能暂停");
    }

    @Override
    public void speed() {
        System.out.println("Error，视频正处在关闭状态，不能倍速播放");
    }

    @Override
    public void close() {
        System.out.println("视频正处在关闭状态");
    }
}
