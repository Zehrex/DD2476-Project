2
https://raw.githubusercontent.com/wxw6860/BLEAPP/master/FastBleLib/src/main/java/com/clj/fastble/exception/TimeoutException.java
package com.clj.fastble.exception;


public class TimeoutException extends BleException {

    public TimeoutException() {
        super(ERROR_CODE_TIMEOUT, "Timeout Exception Occurred!");
    }

}
