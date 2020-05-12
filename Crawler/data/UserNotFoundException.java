2
https://raw.githubusercontent.com/joneconsulting/springbootproject/master/src/main/java/com/example/demo/exception/UserNotFoundException.java
package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
