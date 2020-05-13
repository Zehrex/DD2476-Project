1
https://raw.githubusercontent.com/ryewen/quickbuy/master/src/main/java/com/loststars/quickbuy/controller/BaseController.java
package com.loststars.quickbuy.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.loststars.quickbuy.error.BusinessException;
import com.loststars.quickbuy.error.EmBusinessError;
import com.loststars.quickbuy.response.CommonReturnType;

import org.springframework.http.HttpStatus;

public class BaseController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handerException(HttpServletRequest request, Exception ex) {
        Map<String, Object> reponseData = new HashMap<>();
        BusinessException businessException = null;
        if (ex instanceof BusinessException) {
            businessException = (BusinessException) ex;
        } else {
            businessException = new BusinessException(EmBusinessError.UNKNOW_ERROR);
            ex.printStackTrace();
        }
        reponseData.put("errCode", businessException.getErrCode());
        reponseData.put("errMsg", businessException.getErrMsg());
        return CommonReturnType.createFail(reponseData);
    }
}
