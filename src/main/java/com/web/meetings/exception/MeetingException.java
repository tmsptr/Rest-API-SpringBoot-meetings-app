package com.web.meetings.exception;

public class MeetingException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public MeetingException(String errorMessage) {  
    	super(errorMessage);  
    } 
}
