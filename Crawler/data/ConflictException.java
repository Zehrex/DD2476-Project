2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/web/rest/errors/ConflictException.java
package com.gardle.web.rest.errors;

import org.zalando.problem.Status;

public class ConflictException extends GardleRestControllerException {
    private static final long serialVersionUID = 1L;


    public ConflictException(GardleErrorKey errorKey, String details) {
        super(errorKey, Status.CONFLICT, details);
    }

    public ConflictException(GardleErrorKey errorKey) {
        this(errorKey, null);
    }
}
