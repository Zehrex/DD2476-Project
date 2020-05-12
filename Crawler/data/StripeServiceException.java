2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/StripeServiceException.java
package com.gardle.service.exception;

public class StripeServiceException extends PaymentProviderServiceException {

    public StripeServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
