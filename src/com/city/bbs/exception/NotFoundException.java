package com.city.bbs.exception;

@SuppressWarnings("serial")
public class NotFoundException extends Exception {
	private String message;
	
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public NotFoundException(String message) {
		super(message);
		this.message = message;
	}
	
	
}
