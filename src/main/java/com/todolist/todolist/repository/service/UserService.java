package com.todolist.todolist.repository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.todolist.todolist.domain.User;
import com.todolist.todolist.domain.enums.Roles;
import com.todolist.todolist.repository.UserRepository;
import com.todolist.todolist.repository.service.exception.DataIntegrityException;
import com.todolist.todolist.repository.service.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired UserRepository userRepository;
	
	@Autowired BCryptPasswordEncoder encoder;
	
	public User save(User user) {
		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new DataIntegrityException("Email already exists");
		}
		
		user.setPassword(encoder.encode(user.getPassword()));
		user.addRole(Roles.USER);
		return userRepository.save(user);
	}
	
	public User findById(Integer id) {
		return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + User.class.getName()));
	}
}
