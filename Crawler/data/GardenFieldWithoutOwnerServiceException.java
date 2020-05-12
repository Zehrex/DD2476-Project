2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/GardenFieldWithoutOwnerServiceException.java
package com.gardle.service.exception;

public class GardenFieldWithoutOwnerServiceException extends RuntimeException {

    public GardenFieldWithoutOwnerServiceException() {
        super("Garden field without owner!");
    }
}
