2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/BankAccountCreationEmailEmptyServiceException.java
package com.gardle.service.exception;

public class BankAccountCreationEmailEmptyServiceException extends RuntimeException {
    public BankAccountCreationEmailEmptyServiceException() {
        super("Bank account email emtpy");
    }

    public BankAccountCreationEmailEmptyServiceException(String message) {
        super(message);
    }

    public BankAccountCreationEmailEmptyServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
