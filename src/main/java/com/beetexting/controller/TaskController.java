package com.beetexting.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beetexting.constant.TaskExceptionConstants;
import com.beetexting.customException.BeetextingException;
import com.beetexting.entity.Task;
import com.beetexting.service.TaskService;

/**
 * TaskController will handle CRUD operations for Task Entity
 */
@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;

	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

	/**
	 * 
	 * @param task
	 * @return
	 * 
	 * createTask will create a new task entity
	 */
	@PostMapping("/createTask")
	public ResponseEntity<Task> createTask(@RequestBody Task task) {
		logger.info("Invoked API /task/createTask with requested body task: {}", task);
		Task taskCreated = taskService.createTask(task);
		ResponseEntity<Task> response = ResponseEntity.ok(taskCreated);
		return response;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * 
	 * getTaskById will fetch task entity by input id
	 */
	@GetMapping("/getTaskById")
	public ResponseEntity<Task> getTaskById(@RequestParam Long id) {
		logger.info("Invoked API /task/getTaskById?id={id} with id: {}", id);
		
		if (id == 0 || id == null) {
			logger.error("id is invalid with id: {}", id);
			throw new BeetextingException("id is invalid", TaskExceptionConstants.INVALID_REQUEST_FIELD);
		}
		
		Task task = taskService.getTaskById(id);
		ResponseEntity<Task> response = ResponseEntity.ok(task);
		return response;
	}
	
	/**
	 * 
	 * @param task
	 * @return
	 * 
	 * updateTask will update the existing task with id
	 */
	@PostMapping("/updateTask")
	public ResponseEntity<Task> updateTask(@RequestParam Task task) {
		logger.info("Invoked API /task/updateTask with requested body task: {}", task);
		
		if (task.getId() == 0 || task.getId() == null) {
			logger.error("id is invalid with id: {}", task.getId());
			throw new BeetextingException("id is invalid", TaskExceptionConstants.INVALID_REQUEST_FIELD);
		}
		
		Task taskUpdated = taskService.updateTask(task);
		ResponseEntity<Task> response = ResponseEntity.ok(taskUpdated);
		return response;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * 
	 * deleteTaskById will delete existing task by id
	 */
	@DeleteMapping("/deleteTaskById")
	public ResponseEntity<String> deleteTaskById(@RequestParam Long id) {
		logger.info("Invoked API /task/deleteTaskById?id={id} with id: {}", id);
		
		if (id == 0 || id == null) {
			logger.error("id is invalid with id: {}", id);
			throw new BeetextingException("id is invalid", TaskExceptionConstants.INVALID_REQUEST_FIELD);
		}
		
		taskService.deleteTaskById(id);
		ResponseEntity<String> response = ResponseEntity.ok("Succesfully deleted task");
		return response;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * 
	 * deleteTaskById will delete existing task by id
	 */
	@GetMapping("/markTaskAsCompleted")
	public ResponseEntity<Task> markTaskAsCompleted(@RequestParam Long id) {
		logger.info("Invoked API /task/markTaskAsCompleted?id={id} with id: {}", id);
		
		if (id == 0 || id == null) {
			logger.error("id is invalid with id: {}", id);
			throw new BeetextingException("id is invalid", TaskExceptionConstants.INVALID_REQUEST_FIELD);
		}
		
		Task taskUpdated = taskService.markTaskAsCompleted(id);
		ResponseEntity<Task> response = ResponseEntity.ok(taskUpdated);
		return response;
	}

}
