2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/PaymentServiceException.java
package com.gardle.service.exception;

public class PaymentServiceException extends RuntimeException {

    public PaymentServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
