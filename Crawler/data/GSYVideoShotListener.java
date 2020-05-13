2
https://raw.githubusercontent.com/jarryleo/GSYVideoPlayer/master/gsyVideoPlayer-java/src/main/java/com/shuyu/gsyvideoplayer/listener/GSYVideoShotListener.java
package com.shuyu.gsyvideoplayer.listener;

import android.graphics.Bitmap;

/**
 * 截屏bitmap返回
 * Created by guoshuyu on 2017/9/21.
 */

public interface GSYVideoShotListener {
    void getBitmap(Bitmap bitmap);
}
