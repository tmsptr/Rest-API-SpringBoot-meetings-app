package com.web.meetings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for MVC (MvcConfig.java)
 */
@Controller
public class HomeController {

	/**
	 * Handles GET requests at / HTTP end-point and returns the name of the Thymeleaf view (home) to render an HTML form.
	 */
	@GetMapping(value = "/") // / -> home
	public String showHomeForm() {
		return "home";
	}
}
