package com.todolist.todolist.domain.enums;

public enum Roles {
	
	USER(1, "ROLE_USER"),
	ADMIN(2, "ROLE_ADMIN");
	
	private int code;
	private String description;
	
	private Roles(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Roles toEnum(Integer code) {
		
		if (code == null) {
			return null;
		}
		
		for (Roles x : Roles.values()) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID " + code);
	}
}
