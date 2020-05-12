2
https://raw.githubusercontent.com/jarryleo/GSYVideoPlayer/master/gsyVideoPlayer-java/src/main/java/com/shuyu/gsyvideoplayer/listener/GSYMediaPlayerListener.java
package com.shuyu.gsyvideoplayer.listener;

public interface GSYMediaPlayerListener {
    void onPrepared();

    void onAutoCompletion();

    void onCompletion();

    void onBufferingUpdate(int percent);

    void onSeekComplete();

    void onError(int what, int extra);

    void onInfo(int what, int extra);

    void onVideoSizeChanged();

    void onBackFullscreen();

    void onVideoPause();

    void onVideoResume();

    void onVideoResume(boolean seek);
}
