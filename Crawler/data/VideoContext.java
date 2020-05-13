2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/state/VideoContext.java
package com.wz.behavioral.state;

/**
 * @author 隔壁老王
 * @create 2020-05-10 15:25
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//环境角色
public class VideoContext {

    private VideoState videoState;

    public static final VideoPlayState PLAY_STATE = new VideoPlayState();
    public static final VideoPauseState VIDEO_PAUSE_STATE = new VideoPauseState();
    public static final VideoSpeedState VIDEO_SPEED_STATE = new VideoSpeedState();
    public static final VideoCloseState VIDEO_CLOSE_STATE = new VideoCloseState();

    public void setVideoState(VideoState videoState) {
        this.videoState = videoState;
        this.videoState.setVideoContext(this);
    }

    public VideoState getVideoState() {
        return videoState;
    }

    public void play(){
        this.videoState.play();
    }

    public void pause(){
        this.videoState.pause();
    }
    public void speed(){
        this.videoState.speed();
    }
    public void close(){
        this.videoState.close();
    }
}
