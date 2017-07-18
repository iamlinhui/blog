package com.blog.exception;

public class NoUserLoginException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoUserLoginException(String msg) {
		super(msg);
	}

}
