package com.todolist.todolist.dto;

import java.io.Serializable;

public class CredentialsDTO implements Serializable{
	private static final long serialVersionUID = 4688419890377733155L;
	
	private  String email;
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
