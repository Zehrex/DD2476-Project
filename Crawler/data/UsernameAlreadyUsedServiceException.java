2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/UsernameAlreadyUsedServiceException.java
package com.gardle.service.exception;

public class UsernameAlreadyUsedServiceException extends RuntimeException {

    public UsernameAlreadyUsedServiceException() {
        super("Login name already used!");
    }

}
