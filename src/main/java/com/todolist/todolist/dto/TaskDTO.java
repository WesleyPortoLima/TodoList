package com.todolist.todolist.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.todolist.todolist.domain.Task;
import com.todolist.todolist.domain.enums.Status;

public class TaskDTO implements Serializable {
	private static final long serialVersionUID = -8254763335480110691L;
	
	private Integer id;
	private String name;
	private String description;
	private Status status;
	private LocalDate timestamp;
	
	public TaskDTO() {}
	
	public TaskDTO(Task task) {
		this.id = task.getId();
		this.name = task.getName();
		this.description = task.getDescription();
		this.status = task.getStatus();
		this.timestamp = task.getTimestamp();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public LocalDate getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}
}
