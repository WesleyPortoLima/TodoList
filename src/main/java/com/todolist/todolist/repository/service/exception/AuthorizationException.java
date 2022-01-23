package com.todolist.todolist.repository.service.exception;

public class AuthorizationException extends RuntimeException {
	private static final long serialVersionUID = 8671924547326853788L;

	public AuthorizationException(String msg) {
		super(msg);
	}
	
	public AuthorizationException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
