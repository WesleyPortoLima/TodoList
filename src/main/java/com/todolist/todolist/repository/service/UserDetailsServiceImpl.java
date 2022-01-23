package com.todolist.todolist.repository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todolist.todolist.domain.User;
import com.todolist.todolist.repository.UserRepository;
import com.todolist.todolist.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new UserSS(user.getId(), user.getEmail(), user.getPassword(), user.getRoles());
	}

}
