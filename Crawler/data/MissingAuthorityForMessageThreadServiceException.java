2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/MissingAuthorityForMessageThreadServiceException.java
package com.gardle.service.exception;

import lombok.Getter;

@Getter
public class MissingAuthorityForMessageThreadServiceException extends RuntimeException {
    public MissingAuthorityForMessageThreadServiceException() {

    }

    public MissingAuthorityForMessageThreadServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
