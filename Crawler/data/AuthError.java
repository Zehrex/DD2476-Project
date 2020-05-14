23
https://raw.githubusercontent.com/mrchengwenlong/NettyIM/master/im_lib/src/main/java/com/takiku/im_lib/exception/AuthError.java
package com.takiku.im_lib.exception;

import android.provider.Settings;

public class AuthError extends Throwable {
    public AuthError(String msg){
        super(msg);
    }
}
