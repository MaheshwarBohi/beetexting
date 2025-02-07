package com.beetexting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BeetextingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeetextingApplication.class, args);
	}

}
