2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/ImageStorageServiceException.java
package com.gardle.service.exception;

public class ImageStorageServiceException extends RuntimeException {
    public ImageStorageServiceException(String message) {
        super(message);
    }

    public ImageStorageServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
