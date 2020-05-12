2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/MissingAuthorityServiceException.java
package com.gardle.service.exception;

import lombok.Getter;

@Getter
public class MissingAuthorityServiceException extends RuntimeException {
    public MissingAuthorityServiceException() {

    }

    public MissingAuthorityServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
