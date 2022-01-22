package com.todolist.todolist.repository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.todolist.domain.Task;
import com.todolist.todolist.repository.TaskRepository;
import com.todolist.todolist.repository.service.exception.ObjectNotFoundException;

@Service
public class TaskService {
	
	@Autowired TaskRepository taskRepository;
	
	public Task getTaskById(Integer id) {
		return taskRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Task.class.getName()));
	}
}
