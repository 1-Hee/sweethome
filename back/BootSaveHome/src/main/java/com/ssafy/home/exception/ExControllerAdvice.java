package com.ssafy.home.exception;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerErrorException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {
	
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler
	public ResponseEntity<?> NotFoundHandler(NotFoundException e) {
		log.error("[exceptionHandler] ex", e);
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	@ExceptionHandler
//	public ResponseEntity<?> ServerErrorHandler(ServerErrorException e) {
//		log.error("[exceptionHandler] ex", e);
//		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	@ExceptionHandler
	public ResponseEntity<?> passwordHandler(PasswordException e) {
		log.error("[exceptionHandler] ex", e);
		ErrorResult errorResult = new ErrorResult("1000", e.getMessage());
		return new ResponseEntity<ErrorResult>(errorResult,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<?> idHandler(NoIDException e) {
		log.error("[exceptionHandler] ex", e);
		ErrorResult errorResult = new ErrorResult("1001", e.getMessage());
		return new ResponseEntity<ErrorResult>(errorResult,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<?> exHandler(Exception e) {
		log.error("[exceptionHandler] ex", e);
		ErrorResult errorResult = new ErrorResult("INTERNAL_SERVER_ERROR", e.getMessage());
		return new ResponseEntity<ErrorResult>(errorResult,HttpStatus.INTERNAL_SERVER_ERROR);
	} 
}
