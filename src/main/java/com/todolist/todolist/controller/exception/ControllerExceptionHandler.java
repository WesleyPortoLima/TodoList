package com.todolist.todolist.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.todolist.todolist.repository.service.exception.AuthorizationException;
import com.todolist.todolist.repository.service.exception.DataIntegrityException;
import com.todolist.todolist.repository.service.exception.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		StandardError err = 
				new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Not found", e.getMessage(), request.getRequestURI()); 
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request) {
		
		StandardError err = 
				new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(), "Access denied.", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		
		StandardError err = 
				new StandardError(System.currentTimeMillis(), HttpStatus.CONFLICT.value(), "Email already exists.", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}
}
