2
https://raw.githubusercontent.com/jarryleo/GSYVideoPlayer/master/gsyVideoPlayer-java/src/main/java/com/shuyu/gsyvideoplayer/player/IPlayerInitSuccessListener.java
package com.shuyu.gsyvideoplayer.player;

import com.shuyu.gsyvideoplayer.model.GSYModel;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 播放器初始化成果回调
 */
public interface IPlayerInitSuccessListener {
    void onPlayerInitSuccess(IMediaPlayer player, GSYModel model);
}
