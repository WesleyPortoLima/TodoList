package com.todolist.todolist.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.todolist.todolist.domain.enums.Status;

@Entity
public class Task implements Serializable {
	private static final long serialVersionUID = 3660043560258951757L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String description;
	
	private LocalDate timestamp;
	
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Task() {}

	
	
	public Task(Integer id, String name, String description, LocalDate timestamp, Status status, User user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.timestamp = timestamp;
		this.status = status;
		this.user = user;
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

	public LocalDate getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
