package com.techtech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {
	
	public ResponseEntity<ErrorMessage> generateIt(DogNotFoundException exception){
		ErrorMessage errorMessage= new ErrorMessage();
		errorMessage.setCode("R9999");
		errorMessage.setMessage(exception.getMessage());
		return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
		
	}

}
