package com.todolist.todolist.repository.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.todolist.domain.Task;
import com.todolist.todolist.domain.enums.Status;
import com.todolist.todolist.dto.TaskDTO;
import com.todolist.todolist.repository.TaskRepository;
import com.todolist.todolist.repository.UserRepository;
import com.todolist.todolist.repository.service.exception.AuthorizationException;
import com.todolist.todolist.repository.service.exception.ObjectNotFoundException;
import com.todolist.todolist.security.UserSS;

@Service
public class TaskService {
	
	@Autowired TaskRepository taskRepository;
	
	@Autowired UserService userService;
	
	@Autowired UserRepository userRepository;
	
	public Task findById(Integer id) {
		UserSS user = AuthenticatedUserService.authenticated();
		
		if (user == null) {
			throw new AuthorizationException("Access Denied");
		} else {
			Task task = taskRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Task.class.getName()));
			if (!task.getUser().getId().equals(user.getId())) {
				throw new AuthorizationException("Access Denied");
			} else {
				return task;
			}
		}
	}
	
	public Task save(Task task) {
		UserSS user = AuthenticatedUserService.authenticated();
		task.setUser(userService.findById(task.getUser().getId()));
		if (user == null || !task.getUser().getId().equals(user.getId())) {
			throw new AuthorizationException("Access Denied");
		}
		
		task.setId(null);
		task.setTimestamp(LocalDate.now());
		
		
		task = taskRepository.save(task);
		
		return task;
	}
	
	public Task update(Task task) {
		Task newTask = findById(task.getId());
		updateData(task, newTask);
		
		return taskRepository.save(newTask);
	}
	
	public void delete(Integer id) {
		findById(id);
		taskRepository.deleteById(id);
	}

	private void updateData(Task task, Task newTask) {
		newTask.setName(task.getName());
		newTask.setDescription(task.getDescription());
		newTask.setStatus(task.getStatus());
	}
	
	public List<Task> findAllTasks(String search, Status status) {
		UserSS user = AuthenticatedUserService.authenticated();
		
		if (user == null) {
			throw new AuthorizationException("Access Denied");
		}
		
		if (status == Status.ARCHIVED) {
			return taskRepository.findAllArchivedTasks(user.getId(), search != null ? search : "", status);
		} else {
			return taskRepository.findAllActiveTasks(user.getId(), search != null ? search : "", Status.ARCHIVED);			
		}
	}
	
	public Task fromDTO(TaskDTO dto) {
		return new Task(dto.getId(), dto.getName(), dto.getDescription(), null, dto.getStatus(), null);
	}
}
