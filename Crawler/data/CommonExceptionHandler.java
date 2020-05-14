12
https://raw.githubusercontent.com/zhuchangwu/lawyer-lover-cloud-backend/master/lawyer-lover-main/src/main/java/com/changwu/exception/CommonExceptionHandler.java
package com.changwu.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author: Changwu
 * @Date: 2019/11/6 15:45
 */
@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(MyException.class)
    public ResponseEntity<ExceptionResult> handleExeception(MyException e){
        return ResponseEntity.status(e.getExceptionEnum().getCode()).body(new ExceptionResult(e.getExceptionEnum()));
    }
}
