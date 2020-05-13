2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/UserForLeasingDoesNotExistServiceException.java
package com.gardle.service.exception;

public class UserForLeasingDoesNotExistServiceException extends RuntimeException {
    public UserForLeasingDoesNotExistServiceException() {
        super("User not found!");
    }

    public UserForLeasingDoesNotExistServiceException(String message) {
        super(message);
    }

    public UserForLeasingDoesNotExistServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
