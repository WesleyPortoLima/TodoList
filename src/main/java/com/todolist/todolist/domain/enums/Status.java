package com.todolist.todolist.domain.enums;

public enum Status {
	
	TODO(1, "To do"),
	DOING(2, "Doing"),
	DONE(3, "Done"),
	ARCHIVED(4, "Archived");

	private int code;
	private String description;
	
	private Status(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Status toEnum(Integer code) {
		
		if (code == null) {
			return null;
		}
		
		for (Status x : Status.values()) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID " + code);
	}
}
