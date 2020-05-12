2
https://raw.githubusercontent.com/jarryleo/GSYVideoPlayer/master/gsyVideoPlayer-java/src/main/java/com/shuyu/gsyvideoplayer/listener/GSYVideoShotSaveListener.java
package com.shuyu.gsyvideoplayer.listener;


import java.io.File;

/**
 * 截屏保存结果
 * Created by guoshuyu on 2017/9/21.
 */

public interface GSYVideoShotSaveListener {
    void result(boolean success, File file);
}
