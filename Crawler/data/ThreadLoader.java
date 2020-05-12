4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/util/ThreadLoader.java
package com.sketch.code.two.util;

import android.os.Handler;
import android.os.Message;

public class ThreadLoader {
    Runnable onSuccess;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case 0x10:
                    onSuccess.run();
                    break;
            }
        }
    };
    public ThreadLoader(final Runnable runnable, Runnable onSuccess) {
        this.onSuccess = onSuccess;
        new Thread(new Runnable() {
            @Override
            public void run() {
                runnable.run();
                handler.sendEmptyMessage(0x10);
            }
        }).start();
    }
}
