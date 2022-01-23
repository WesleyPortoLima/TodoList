package com.todolist.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.todolist.todolist.domain.Task;
import com.todolist.todolist.domain.enums.Status;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{

	@Query(value = "SELECT t FROM Task t WHERE t.user.id=:userId and t.name LIKE CONCAT(:search,'%') and t.status <> :status")
	List<Task> findAllActiveTasks(@Param("userId")Integer userId, @Param("search")String search, @Param("status")Status status);
	
	@Query(value = "SELECT t FROM Task t WHERE t.user.id=:userId and t.name LIKE CONCAT(:search,'%') and t.status=:status")
	List<Task> findAllArchivedTasks(@Param("userId")Integer userId, @Param("search")String search, @Param("status")Status status);

}
