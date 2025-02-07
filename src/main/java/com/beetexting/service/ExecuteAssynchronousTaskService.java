package com.beetexting.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ExecuteAssynchronousTaskService {

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
	}

}
