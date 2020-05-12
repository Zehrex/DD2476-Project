2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/exception/ImageStorageServiceDeletionException.java
package com.gardle.service.exception;

public class ImageStorageServiceDeletionException extends RuntimeException {
    public ImageStorageServiceDeletionException(String message) {
        super(message);
    }

    public ImageStorageServiceDeletionException(String message, Throwable cause) {
        super(message, cause);
    }
}
