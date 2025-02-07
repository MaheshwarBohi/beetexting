package com.beetexting.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.beetexting.constant.Constants;
import com.beetexting.constant.TaskExceptionConstants;
import com.beetexting.customException.BeetextingException;
import com.beetexting.entity.Task;
import com.beetexting.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

	public Task createTask(Task task) {

		try {
			task = taskRepository.save(task);
			logger.info("successfully created task: {}", task);
			return task;
		} catch (DataAccessException e) {
			logger.error("failed at databse execution with exception: {}", e.getMessage());
			throw new BeetextingException("failed at database execution", TaskExceptionConstants.DATABASE_EXCEPTION);
		}
	}

	public Task getTaskById(Long id) {

		try {
			Optional<Task> optionalTask = taskRepository.findById(id);
			if (!optionalTask.isPresent()) {
				logger.error("task entity not found with id: {}", id);
				throw new BeetextingException("task not found", TaskExceptionConstants.ENTITY_NOT_FOUND);
			}

			Task task = optionalTask.get();
			logger.info("successfully fetched task: {}", task);
			return task;
		} catch (DataAccessException e) {
			logger.error("failed at databse execution with exception: {}", e.getMessage());
			throw new BeetextingException("failed at database execution", TaskExceptionConstants.DATABASE_EXCEPTION);
		}
	}

	public Task updateTask(Task task) {

		try {
			Optional<Task> optionalTask = taskRepository.findById(task.getId());
			if (!optionalTask.isPresent()) {
				logger.error("task entity not found with id: {}", task.getId());
				throw new BeetextingException("task not found", TaskExceptionConstants.ENTITY_NOT_FOUND);
			}
			
			Task newTask = optionalTask.get();
			newTask.setDescription(task.getDescription());
			newTask.setStatus(task.getStatus());
			newTask.setDueDate(task.getDueDate());
			newTask.setUserId(task.getUserId());
			
			newTask = taskRepository.save(newTask);
			logger.info("successfully updated task: {}", newTask);
			return newTask;
		} catch (DataAccessException e) {
			logger.error("failed at databse execution with exception: {}", e.getMessage());
			throw new BeetextingException("failed at database execution", TaskExceptionConstants.DATABASE_EXCEPTION);
		}
	}

	public void deleteTaskById(Long id) {

		try {
			Optional<Task> optionalTask = taskRepository.findById(id);
			if (!optionalTask.isPresent()) {
				logger.error("task entity not found with id: {}", id);
				throw new BeetextingException("task not found", TaskExceptionConstants.ENTITY_NOT_FOUND);
			}

			taskRepository.deleteById(optionalTask.get().getId());
			logger.info("successfully deleted task: {}", optionalTask.get());
		} catch (DataAccessException e) {
			logger.error("failed at databse execution with exception: {}", e.getMessage());
			throw new BeetextingException("failed at database execution", TaskExceptionConstants.DATABASE_EXCEPTION);
		}
	}
	
	public Task markTaskAsCompleted(Long id) {

		try {
			Optional<Task> optionalTask = taskRepository.findById(id);
			if (!optionalTask.isPresent()) {
				logger.error("task entity not found with id: {}", id);
				throw new BeetextingException("task not found", TaskExceptionConstants.ENTITY_NOT_FOUND);
			}
			
			Task newTask = optionalTask.get();
			newTask.setStatus(Constants.COMPLETED);
			
			/**
			 * TODO send mail using Spring Mail when task is marked as Completed
			 */
			
			newTask = taskRepository.save(newTask);
			logger.info("successfully updated task: {}", newTask);
			return newTask;
		} catch (DataAccessException e) {
			logger.error("failed at databse execution with exception: {}", e.getMessage());
			throw new BeetextingException("failed at database execution", TaskExceptionConstants.DATABASE_EXCEPTION);
		}
	}
	
}
