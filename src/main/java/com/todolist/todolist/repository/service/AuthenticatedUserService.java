package com.todolist.todolist.repository.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.todolist.todolist.security.UserSS;

public class AuthenticatedUserService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	} 
}
