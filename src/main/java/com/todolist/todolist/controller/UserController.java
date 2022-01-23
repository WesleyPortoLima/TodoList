package com.todolist.todolist.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todolist.todolist.domain.User;
import com.todolist.todolist.repository.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired UserService userService;
	
	@RequestMapping(value = "/{id}",method=RequestMethod.GET)
	public ResponseEntity<User> find(@PathVariable final Integer id) {
		User user = userService.findById(id);

		return ResponseEntity.ok().body(user);
	}
	
	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody User user) {
		User savedUser = userService.save(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	} 
}
