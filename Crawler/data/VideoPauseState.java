2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/state/VideoPauseState.java
package com.wz.behavioral.state;

/**
 * @author 隔壁老王
 * @create 2020-05-10 15:28
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体状态角色：暂停状态
public class VideoPauseState extends VideoState {
    @Override
    public void play() {
        super.videoContext.setVideoState(VideoContext.PLAY_STATE);
    }

    @Override
    public void pause() {
        System.out.println("视频正处在暂停状态");
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
