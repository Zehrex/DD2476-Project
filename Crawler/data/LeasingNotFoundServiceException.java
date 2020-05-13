2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/LeasingNotFoundServiceException.java
package com.gardle.service.exception;

public class LeasingNotFoundServiceException extends RuntimeException {

    public LeasingNotFoundServiceException() {
        super("Gardenfield not found!");
    }

    public LeasingNotFoundServiceException(String message) {
        super(message);
    }

    public LeasingNotFoundServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
