package com.web.meetings.exception;

public class MeetingException extends RuntimeException{
	private static final long serialVersionUID = 1L; // Deserialization uses this number to ensure that a loaded class corresponds exactly to a serialized object. If no match is found, then an InvalidClassException is thrown.

	public MeetingException(String errorMessage) {  
    	super(errorMessage);  
    } 
}
