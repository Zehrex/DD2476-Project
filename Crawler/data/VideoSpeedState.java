2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/state/VideoSpeedState.java
package com.wz.behavioral.state;

/**
 * @author 隔壁老王
 * @create 2020-05-10 15:29
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体状态角色：倍速状态
public class VideoSpeedState extends VideoState  {
    @Override
    public void play() {
        super.videoContext.setVideoState(VideoContext.PLAY_STATE);
    }

    @Override
    public void pause() {
        super.videoContext.setVideoState(VideoContext.VIDEO_PAUSE_STATE);
    }

    @Override
    public void speed() {
        System.out.println("视频正处在倍速播放状态");
    }

    @Override
    public void close() {
        super.videoContext.setVideoState(VideoContext.VIDEO_CLOSE_STATE);
    }
}
