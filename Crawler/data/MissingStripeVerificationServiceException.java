2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/MissingStripeVerificationServiceException.java
package com.gardle.service.exception;

public class MissingStripeVerificationServiceException extends RuntimeException {
    public MissingStripeVerificationServiceException() {
    }

    public MissingStripeVerificationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
