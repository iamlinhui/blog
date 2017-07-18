package com.blog.exception;

public class AdminAccessForbiddenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AdminAccessForbiddenException(String msg) {
		super(msg);
	}

}
