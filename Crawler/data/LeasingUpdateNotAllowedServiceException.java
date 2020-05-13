2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/LeasingUpdateNotAllowedServiceException.java
package com.gardle.service.exception;

public class LeasingUpdateNotAllowedServiceException extends RuntimeException {
    public LeasingUpdateNotAllowedServiceException() {
        super("Leasing update is not allowed in this period!");
    }

    public LeasingUpdateNotAllowedServiceException(String message) {
        super(message);
    }

    public LeasingUpdateNotAllowedServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
