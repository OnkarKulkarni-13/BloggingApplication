package com.bloggingapplication.exception;

public class UserNameNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String resourceName;
	String fieldName;
	String userName;

	public UserNameNotFoundException(String resourceName, String fieldName, String userName) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName, userName));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.userName = userName;
	}

}
