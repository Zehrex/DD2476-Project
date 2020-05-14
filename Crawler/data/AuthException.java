23
https://raw.githubusercontent.com/mrchengwenlong/NettyIM/master/im_lib/src/main/java/com/takiku/im_lib/exception/AuthException.java
package com.takiku.im_lib.exception;

public class AuthException extends Exception {

    public AuthException(AuthError throwable){
        super(throwable);
    }
}
