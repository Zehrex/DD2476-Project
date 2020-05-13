2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/EmailAlreadyUsedServiceException.java
package com.gardle.service.exception;

public class EmailAlreadyUsedServiceException extends RuntimeException {

    public EmailAlreadyUsedServiceException() {
        super("Email is already in use!");
    }

}
