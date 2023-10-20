package com.udemy.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.todo.entity.Todo;

public interface TodoRepository  extends JpaRepository<Todo, Long>{
	

}
