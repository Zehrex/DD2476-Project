2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/InvalidPasswordServiceException.java
package com.gardle.service.exception;

public class InvalidPasswordServiceException extends RuntimeException {

    public InvalidPasswordServiceException() {
        super("Incorrect password");
    }

}
