2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/web/rest/errors/UnauthorizedException.java
package com.gardle.web.rest.errors;

import org.zalando.problem.Status;

public class UnauthorizedException extends GardleRestControllerException {
    private static final long serialVersionUID = 1L;

    public UnauthorizedException(GardleErrorKey errorKey, String details) {
        super(errorKey, Status.UNAUTHORIZED, details);
    }

    public UnauthorizedException(GardleErrorKey errorKey) {
        this(errorKey, null);
    }
}
