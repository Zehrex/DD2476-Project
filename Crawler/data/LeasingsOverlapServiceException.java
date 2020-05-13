2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/LeasingsOverlapServiceException.java
package com.gardle.service.exception;

public class LeasingsOverlapServiceException extends RuntimeException {

    public LeasingsOverlapServiceException() {
        super("Leasings cannot overlap");
    }
}
