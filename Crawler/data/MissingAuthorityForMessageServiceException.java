2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/MissingAuthorityForMessageServiceException.java
package com.gardle.service.exception;

public class MissingAuthorityForMessageServiceException extends RuntimeException {
    public MissingAuthorityForMessageServiceException() {

    }

    public MissingAuthorityForMessageServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
