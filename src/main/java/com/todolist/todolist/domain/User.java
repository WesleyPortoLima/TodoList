package com.todolist.todolist.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.todolist.todolist.domain.enums.Roles;

@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 8859813789764091013L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name = "roles")
	private Set<Integer> roles = new HashSet<Integer>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Task> tasks = new ArrayList<>();

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

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public Set<Roles> getRoles() {
		return roles.stream().map(x -> Roles.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addRole(Roles role) {
		roles.add(role.getCode());
	}
}
