package com.todolist.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todolist.todolist.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
