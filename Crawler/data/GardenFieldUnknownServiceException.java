2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/GardenFieldUnknownServiceException.java
package com.gardle.service.exception;

public class GardenFieldUnknownServiceException extends RuntimeException {
    public GardenFieldUnknownServiceException() {
        super("Gardenfield not existing!");
    }

    public GardenFieldUnknownServiceException(String message) {
        super(message);
    }

    public GardenFieldUnknownServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
