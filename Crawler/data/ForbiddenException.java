2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/web/rest/errors/ForbiddenException.java
package com.gardle.web.rest.errors;

import org.zalando.problem.Status;

public class ForbiddenException extends GardleRestControllerException {
    private static final long serialVersionUID = 1L;

    public ForbiddenException(GardleErrorKey errorKey, String details) {
        super(errorKey, Status.FORBIDDEN, details);
    }

    public ForbiddenException(GardleErrorKey errorKey) {
        this(errorKey, null);
    }
}
