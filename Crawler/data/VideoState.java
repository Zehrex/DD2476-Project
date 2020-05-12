2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/state/VideoState.java
package com.wz.behavioral.state;

/**
 * @author 隔壁老王
 * @create 2020-05-10 15:21
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//业务场景：想必大家都应该在腾讯视频、优酷视频等其他网站看过电影或电视剧。
//当你看影视剧时视频就存在多种状态，播放、暂停、倍速、关闭。
//抽象状态角色：视频状态
public abstract class VideoState {
    protected VideoContext videoContext;

    public void setVideoContext(VideoContext videoContext) {
        this.videoContext = videoContext;
    }

    public abstract void play();
    public abstract void pause();
    public abstract void speed();
    public abstract void close();

}
