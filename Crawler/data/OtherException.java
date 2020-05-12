2
https://raw.githubusercontent.com/wxw6860/BLEAPP/master/FastBleLib/src/main/java/com/clj/fastble/exception/OtherException.java
package com.clj.fastble.exception;


public class OtherException extends BleException {

    public OtherException(String description) {
        super(ERROR_CODE_OTHER, description);
    }

}
