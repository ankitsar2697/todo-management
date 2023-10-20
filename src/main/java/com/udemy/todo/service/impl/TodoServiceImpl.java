package com.udemy.todo.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.udemy.todo.dto.TodoDto;
import com.udemy.todo.entity.Todo;
import com.udemy.todo.exception.ResourceNotFoundException;
import com.udemy.todo.repository.TodoRepository;
import com.udemy.todo.service.TodoService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

	private TodoRepository todoRepository;
	
	private ModelMapper modelMapper;
	
	@Override
	public TodoDto addTodo(TodoDto todoDto) {
		// TODO Auto-generated method stub
//		Todo todo = new Todo();
//		todo.setId(todoDto.getId());
//		todo.setTitle(todoDto.getTitle());
//		todo.setDescription(todoDto.getDescription());
//		todo.setCompleted(todoDto.isCompleted());
		
		Todo todo = modelMapper.map(todoDto, Todo.class);
		
		Todo savedTodo=todoRepository.save(todo);
		
//		TodoDto todoDto2=new TodoDto();
//		todoDto2.setId(savedTodo.getId());
//		todoDto2.setTitle(savedTodo.getTitle());
//		todoDto2.setDescription(savedTodo.getDescription());
//		todoDto2.setCompleted(savedTodo.isCompleted());
		
		TodoDto todoDto2 = modelMapper.map(savedTodo, TodoDto.class);
		 
		return todoDto2;
	}

	@Override
	public List<TodoDto> getTodo() {
		// TODO Auto-generated method stub
		List<Todo> todos =  todoRepository.findAll();
		// List<TodoDto>  gettodoDto =  todo.stream().map((model)-> modelMapper.map(model, TodoDto.class)) ;
		return todos.stream().map((todo)-> modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList());
	}

	@Override
	public TodoDto getTodoById(Long id) {
		// TODO Auto-generated method stub
		Todo getTodo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: "+id));
		return modelMapper.map(getTodo, TodoDto.class);
	}

	@Override
	public TodoDto updateTodo(Long id, TodoDto todoDto) {
		// TODO Auto-generated method stub
		Todo getTodo = todoRepository.findById(id).get();
		Todo updateTodo =  modelMapper.map(todoDto, Todo.class);
		Todo updatedTodo = todoRepository.save(updateTodo);
		TodoDto updatedTodoDto = modelMapper.map(updatedTodo, TodoDto.class);
		
		
		return updatedTodoDto;
	}

	@Override
	public TodoDto completeTodo(Long id) {
		// TODO Auto-generated method stub
		Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found with id " +id));
		todo.setCompleted(Boolean.TRUE);
		Todo updatedTodo = todoRepository.save(todo);
		
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

	@Override
	public void deleteTodoById(Long id) {
		// TODO Auto-generated method stub
	Todo todo =	todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("incorrect id "));
		todoRepository.deleteById(id);
		
	}

	@Override
	public TodoDto incompleteTodo(Long id) {
		// TODO Auto-generated method stub
		Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found"));
		todo.setCompleted(Boolean.FALSE);
		Todo savedTodo = todoRepository.save(todo);
		return modelMapper.map(savedTodo, TodoDto.class);
	}

}
