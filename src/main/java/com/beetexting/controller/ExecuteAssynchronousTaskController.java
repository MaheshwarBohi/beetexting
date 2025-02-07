package com.beetexting.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beetexting.service.ExecuteAssynchronousTaskService;

/**
 * ExecuteAssynchronousTaskController will execute task asynchronously
 */
@RestController
@RequestMapping("/assync")
public class ExecuteAssynchronousTaskController {

	@Autowired
	private ExecuteAssynchronousTaskService executeAssynchronousTaskService;

	private static final Logger logger = LoggerFactory.getLogger(ExecuteAssynchronousTaskController.class);

	/**
	 * 
	 * @return
	 * 
	 *         executeTask will call service executeTask method and initiate task to
	 *         execute asynchronously and return response
	 */
	@GetMapping("/executeTask")
	public ResponseEntity<String> executeTask() {
		logger.info("Invoked API /assync/executeTask");

		String responseString = executeAssynchronousTaskService.executeTask();
		ResponseEntity<String> response = ResponseEntity.ok(responseString);
		return response;
	}

}
