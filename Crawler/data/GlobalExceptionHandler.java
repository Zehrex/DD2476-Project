1
https://raw.githubusercontent.com/vijaykumbam/DrinkanddelightFinalVersion/master/DrinkandDelightsprintOracle/src/main/java/com/capgemini/drinkanddelight/exception/GlobalExceptionHandler.java
package com.capgemini.drinkanddelight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
/*
 * ControllerAdvice + ResponseBody ==> RestControllerAdvice
 */
public class GlobalExceptionHandler {

	//ProductOrderEntityNotFoundException
		
		//DistributorNotFoundExceptionSirMethod
		@ExceptionHandler(value=DistributorNotFoundException.class)
		public ResponseEntity<Object> notFoundException(DistributorNotFoundException distributornotfoundexception){
			return new ResponseEntity<>(distributornotfoundexception.getMessage(),HttpStatus.NOT_FOUND);
		}
	
	
}
