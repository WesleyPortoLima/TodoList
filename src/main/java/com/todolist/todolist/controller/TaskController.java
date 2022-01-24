package com.todolist.todolist.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todolist.todolist.domain.Task;
import com.todolist.todolist.domain.enums.Status;
import com.todolist.todolist.dto.TaskDTO;
import com.todolist.todolist.repository.service.TaskService;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
	
	@Autowired TaskService taskService;
	
	@RequestMapping(value = "/{id}",method=RequestMethod.GET)
	public ResponseEntity<TaskDTO> find(@PathVariable final Integer id) {
		Task task = taskService.findById(id);
		
		return ResponseEntity.ok().body(new TaskDTO(task));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Task task) {
		Task savedTask = taskService.save(task);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(savedTask.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	} 
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<TaskDTO>> findAll(String search, Status status) {
		List<Task> tasks = taskService.findAllTasks(search, status);
		List<TaskDTO> listDTO = tasks.stream().map(x -> new TaskDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable final Integer id) {
		taskService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(
			@Valid @RequestBody TaskDTO dto, 
			@PathVariable final Integer id) {
		Task task = taskService.fromDTO(dto);
		task.setId(id);
		task = taskService.update(task);

		return ResponseEntity.noContent().build();
	} 
	
	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(
			@PathVariable final Integer id) {
		taskService.archiveTask(id);

		return ResponseEntity.noContent().build();
	} 
}
