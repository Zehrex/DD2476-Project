23
https://raw.githubusercontent.com/mrchengwenlong/NettyIM/master/im_lib/src/main/java/com/takiku/im_lib/call/Call.java
package com.takiku.im_lib.call;

import com.takiku.im_lib.entity.base.Request;

public interface Call extends Cloneable{
    Request request();
    void enqueue(Callback responseCallback);
    Call clone();
    interface Factory {
        Call newCall(Request request);
    }
}
