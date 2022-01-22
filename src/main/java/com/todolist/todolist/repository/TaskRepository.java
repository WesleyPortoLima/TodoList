package com.todolist.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todolist.todolist.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{

}
