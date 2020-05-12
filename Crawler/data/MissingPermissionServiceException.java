2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/MissingPermissionServiceException.java
package com.gardle.service.exception;

public class MissingPermissionServiceException extends RuntimeException {

    public MissingPermissionServiceException() {
        super();
    }

    public MissingPermissionServiceException(String message) {
        super(message);
    }

    public MissingPermissionServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
