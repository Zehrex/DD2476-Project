2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/StripeVerificationKeyValidationServiceException.java
package com.gardle.service.exception;

public class StripeVerificationKeyValidationServiceException extends RuntimeException {
    public StripeVerificationKeyValidationServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
