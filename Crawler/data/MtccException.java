11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-common/src/main/java/com/yf/mtcc/common/exception/MtccException.java
package com.yf.mtcc.common.exception;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
public class MtccException extends RuntimeException {

    public MtccException() {
    }

    public MtccException(String message) {
        super(message);
    }

    public MtccException(String message, Throwable cause) {
        super(message, cause);
    }

    public MtccException(Throwable cause) {
        super(cause);
    }
}
