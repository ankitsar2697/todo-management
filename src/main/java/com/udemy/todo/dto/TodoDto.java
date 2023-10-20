package com.udemy.todo.dto;

import lombok.Data;
import lombok.Setter;

@Data
public class TodoDto {

	private Long id;
	private String title;
	private String description;
	private boolean completed;
	
}
