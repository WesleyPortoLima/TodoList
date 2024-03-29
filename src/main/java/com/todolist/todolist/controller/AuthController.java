package com.todolist.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.todolist.repository.service.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
	
	@Autowired UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Boolean> isAuthenticated() {
		boolean isAuthenticated = userService.isAuthenticated();

		return ResponseEntity.ok().body(isAuthenticated);
	}
}
