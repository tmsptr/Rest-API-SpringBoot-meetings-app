package com.web.meetings.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 */
@ControllerAdvice // Allows to use Exception handling methods annotated with @ExceptionHandler
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * In case of disruption in request handling
	 */
	@ExceptionHandler(MeetingException.class) // Allows for extra methods to handle exceptions thrown by request handling (@RequestMapping) methods
	public final ResponseEntity<ErrorResponse> handleCourseException(MeetingException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
