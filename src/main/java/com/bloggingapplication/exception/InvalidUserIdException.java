package com.bloggingapplication.exception;

public class InvalidUserIdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String resourceName;
	String fieldName;
	long fieldValue;

	public InvalidUserIdException(String resourceName, String fieldName, Integer fieldValue) {
		super(String.format("%s user  %s enterd user i'd must be positive : %d", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

}
