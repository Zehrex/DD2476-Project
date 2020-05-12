2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/GardenFieldNotFoundServiceException.java
package com.gardle.service.exception;

public class GardenFieldNotFoundServiceException extends RuntimeException {
    public GardenFieldNotFoundServiceException() {
        super("Gardenfield not found!");
    }

    public GardenFieldNotFoundServiceException(String message) {
        super(message);
    }

    public GardenFieldNotFoundServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
