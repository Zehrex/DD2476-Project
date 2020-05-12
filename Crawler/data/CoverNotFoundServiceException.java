2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/CoverNotFoundServiceException.java
package com.gardle.service.exception;

public class CoverNotFoundServiceException extends RuntimeException {
    public CoverNotFoundServiceException() {
        super("Cover not found!");
    }

    public CoverNotFoundServiceException(String message) {
        super(message);
    }

    public CoverNotFoundServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
