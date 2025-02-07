package com.beetexting.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Task {
	
	@Id
	private Long id;
	
	private String description;
	
	private String status;
	
	private LocalDate dueDate;
	
	private Long userId;

}
