2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/NotLoggedInServiceException.java
package com.gardle.service.exception;

public class NotLoggedInServiceException extends RuntimeException {

    public NotLoggedInServiceException() {

    }


    public NotLoggedInServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
