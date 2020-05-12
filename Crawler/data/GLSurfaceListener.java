2
https://raw.githubusercontent.com/jarryleo/GSYVideoPlayer/master/gsyVideoPlayer-java/src/main/java/com/shuyu/gsyvideoplayer/render/view/listener/GLSurfaceListener.java
package com.shuyu.gsyvideoplayer.render.view.listener;

import android.view.Surface;

/**
 * GLSurfaceView surface 状态变化回调
 * Created by guoshuyu on 2018/1/29.
 */
public interface GLSurfaceListener {
    void onSurfaceAvailable(Surface surface);
}