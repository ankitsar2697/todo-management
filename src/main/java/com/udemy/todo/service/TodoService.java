package com.udemy.todo.service;

import java.util.List;

import com.udemy.todo.dto.TodoDto;

public interface TodoService {

	TodoDto addTodo(TodoDto todoDto);
	
	List<TodoDto> getTodo();
	
	TodoDto getTodoById(Long id);
	
	TodoDto updateTodo(Long id , TodoDto todoDto);
	
	TodoDto completeTodo(Long id);
	
	void deleteTodoById(Long id);
	
	TodoDto incompleteTodo(Long id);
	
}
