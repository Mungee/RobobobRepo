package com.roboticks.robobob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class RobobobApplication {

	public static void main(String[] args) {
		SpringApplication.run(RobobobApplication.class, args);
	}

}
