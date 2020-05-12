2
https://raw.githubusercontent.com/jarryleo/GSYVideoPlayer/master/app/src/main/java/com/example/gsyvideoplayer/utils/floatUtil/FloatView.java
package com.example.gsyvideoplayer.utils.floatUtil;

import android.view.View;

/**
 * Created by yhao on 17-11-14.
 * https://github.com/yhaolpz
 */

abstract class FloatView {

    abstract void setSize(int width, int height);

    abstract void setView(View view);

    abstract void setGravity(int gravity, int xOffset, int yOffset);

    abstract void init();

    abstract void dismiss();

    void updateXY(int x, int y) {
    }

    void updateX(int x) {
    }

    void updateY(int y) {
    }

    int getX() {
        return 0;
    }

    int getY() {
        return 0;
    }
}
