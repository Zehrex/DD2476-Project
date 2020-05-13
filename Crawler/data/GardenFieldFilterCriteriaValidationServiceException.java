2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/GardenFieldFilterCriteriaValidationServiceException.java
package com.gardle.service.exception;

public class GardenFieldFilterCriteriaValidationServiceException extends RuntimeException {

    public GardenFieldFilterCriteriaValidationServiceException() {
        super("Validation exception");
    }

    public GardenFieldFilterCriteriaValidationServiceException(String message) {
        super(message);
    }

    public GardenFieldFilterCriteriaValidationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
