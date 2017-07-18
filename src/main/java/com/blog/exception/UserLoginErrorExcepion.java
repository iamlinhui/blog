package com.blog.exception;

public class UserLoginErrorExcepion extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserLoginErrorExcepion(String msg) {
		super(msg);
	}

}
