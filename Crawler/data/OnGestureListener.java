9
https://raw.githubusercontent.com/TrillGates/TaobaoUnion/master/refreshlibrary/src/main/java/com/lcodecore/tkrefreshlayout/OnGestureListener.java
package com.lcodecore.tkrefreshlayout;

import android.view.MotionEvent;

public interface OnGestureListener {
    void onDown(MotionEvent ev);

    void onScroll(MotionEvent downEvent, MotionEvent currentEvent, float distanceX, float distanceY);

    void onUp(MotionEvent ev, boolean isFling);

    void onFling(MotionEvent downEvent, MotionEvent upEvent, float velocityX, float velocityY);
}