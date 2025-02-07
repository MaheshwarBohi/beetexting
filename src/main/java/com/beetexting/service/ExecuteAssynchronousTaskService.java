package com.beetexting.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.beetexting.constant.Constants;
import com.beetexting.constant.TaskExceptionConstants;
import com.beetexting.customException.BeetextingException;
import com.beetexting.entity.Task;
import com.beetexting.repository.TaskRepository;

@Service
public class ExecuteAssynchronousTaskService {

	@Autowired
	private TaskRepository taskRepository;

	private static final Logger logger = LoggerFactory.getLogger(ExecuteAssynchronousTaskService.class);

	public String executeTask() {
		executeAssynchronousTask();
		return "executing assynchronous task";
	}

	@Async
	public void executeAssynchronousTask() {

		/**
		 * TODO lets make Task entity status as COMPLETED and calling controller API to
		 * initiate notification
		 */

		try {
			List<Task> taskList = taskRepository.findAll();

			taskList.stream().map(task -> {
				task.setStatus(Constants.COMPLETED);
				task = taskRepository.save(task);

				/**
				 * need to send mail via Spring Mail
				 */

				return task;
			}).collect(Collectors.toList());
		} catch (DataAccessException e) {
			logger.error("failed at databse execution with exception: {}", e.getMessage());
			throw new BeetextingException("failed at database execution", TaskExceptionConstants.DATABASE_EXCEPTION);
		}

	}

}
