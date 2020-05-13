2
https://raw.githubusercontent.com/jarryleo/GSYVideoPlayer/master/gsyVideoPlayer-java/src/main/java/com/shuyu/gsyvideoplayer/listener/GSYVideoProgressListener.java
package com.shuyu.gsyvideoplayer.listener;

/**
 * 进度回调
 * Created by guoshuyu on 2017/12/20.
 */
public interface GSYVideoProgressListener {
    /**
     * @param progress 当前播放进度（暂停后再播放可能会有跳动）
     * @param secProgress 当前内存缓冲进度（可能会有0值）
     * @param currentPosition 当前播放位置（暂停后再播放可能会有跳动）
     * @param duration 总时长
     */
    void onProgress(int progress, int secProgress, int currentPosition, int duration);
}
