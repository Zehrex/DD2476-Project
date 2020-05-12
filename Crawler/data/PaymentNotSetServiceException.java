2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/PaymentNotSetServiceException.java
package com.gardle.service.exception;

public class PaymentNotSetServiceException extends RuntimeException {
    public PaymentNotSetServiceException() {
        super("Payment of leasing not set");
    }

    public PaymentNotSetServiceException(String message) {
        super(message);
    }

    public PaymentNotSetServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
