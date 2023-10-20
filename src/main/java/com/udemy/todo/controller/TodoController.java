package com.udemy.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.todo.dto.TodoDto;
import com.udemy.todo.service.TodoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/todos")
public class TodoController {

	private TodoService todoService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("create")
	public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto){
		TodoDto savedtodoDto = todoService.addTodo(todoDto);
		
		return new ResponseEntity<TodoDto>(savedtodoDto,HttpStatus.CREATED);
		
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping
	public ResponseEntity<List<TodoDto>> getAllTodos()
	
	{
		List<TodoDto> getAllTodo = todoService.getTodo();
	return new ResponseEntity<List<TodoDto>>(getAllTodo,HttpStatus.OK);
	
	
	
}
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("{id}")
	public ResponseEntity<TodoDto> getTodoById(@PathVariable Long id){
		TodoDto getTodo = todoService.getTodoById(id);
		return ResponseEntity.ok(getTodo);
		
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("update/{id}")
	public ResponseEntity<TodoDto> updateTodoById(@RequestBody TodoDto todoDto ,@PathVariable Long id){
		todoDto.setId(id);
		TodoDto updatedTodo = todoService.updateTodo(id, todoDto);
		
		return new ResponseEntity<TodoDto>(updatedTodo,HttpStatus.CREATED);
		
	}
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PatchMapping("{id}/complete")
	public ResponseEntity<TodoDto> updateComplete(@PathVariable Long id){
		TodoDto updatedTodo = todoService.completeTodo(id);
		return ResponseEntity.ok(updatedTodo);
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		todoService.deleteTodoById(id);
		return ResponseEntity.ok("Deleted successfully");
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PatchMapping("{id}/in-complete")
	public ResponseEntity<TodoDto> incompleteTodo(@PathVariable Long id){
		TodoDto partiallyUpdated =  todoService.incompleteTodo(id);
		return ResponseEntity.ok(partiallyUpdated);
		
	}
}
