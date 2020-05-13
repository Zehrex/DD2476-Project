2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/MissingAuthorityForGardenFieldServiceException.java
package com.gardle.service.exception;

import lombok.Getter;

@Getter
public class MissingAuthorityForGardenFieldServiceException extends RuntimeException {
    public MissingAuthorityForGardenFieldServiceException() {

    }

    public MissingAuthorityForGardenFieldServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
