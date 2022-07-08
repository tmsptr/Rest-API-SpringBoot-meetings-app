package com.web.meetings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication:
 * Enables Spring Boot’s auto configuration mechanism 
 * Designates the current package as the root package for component scanning.
 * Configuration class moved to MvcConfig.java
 */
@SpringBootApplication
public class MeetingsApplication {

	/**
	 * Entry point
	 */
	public static void main(String[] args) {
		SpringApplication.run(MeetingsApplication.class, args);
	}
}
