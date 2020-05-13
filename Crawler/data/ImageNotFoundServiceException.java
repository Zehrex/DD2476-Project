2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/ImageNotFoundServiceException.java
package com.gardle.service.exception;

public class ImageNotFoundServiceException extends RuntimeException {
    public ImageNotFoundServiceException() {
        super("Image not found!");
    }

    public ImageNotFoundServiceException(String message) {
        super(message);
    }

    public ImageNotFoundServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
