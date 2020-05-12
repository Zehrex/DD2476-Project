2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/state/VideoPlayState.java
package com.wz.behavioral.state;

/**
 * @author 隔壁老王
 * @create 2020-05-10 15:28
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体状态角色：播放状态
public class VideoPlayState extends VideoState {
    @Override
    public void play() {
        System.out.println("视频正处在播放状态");
    }

    @Override
    public void pause() {
        super.videoContext.setVideoState(VideoContext.VIDEO_PAUSE_STATE);
    }

    @Override
    public void speed() {
        super.videoContext.setVideoState(VideoContext.VIDEO_SPEED_STATE);
    }

    @Override
    public void close() {
        super.videoContext.setVideoState(VideoContext.VIDEO_CLOSE_STATE);
    }
}
