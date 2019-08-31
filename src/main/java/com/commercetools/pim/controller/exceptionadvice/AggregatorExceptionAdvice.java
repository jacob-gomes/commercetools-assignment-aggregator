package com.commercetools.pim.controller.exceptionadvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author Jacob
 *
 */
@ControllerAdvice
public class AggregatorExceptionAdvice {

	/**
	 * Handles all Exception
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
	public ResponseEntity<String> handleException(Exception exception) {
		return new ResponseEntity<>( exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
