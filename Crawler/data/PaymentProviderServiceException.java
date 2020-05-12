2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/PaymentProviderServiceException.java
package com.gardle.service.exception;

public class PaymentProviderServiceException extends RuntimeException {
    public PaymentProviderServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
