package com.bloggingapplication.exception;

public class TitleSearchNotPresentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String resourceName;
	String fieldName;
	String titleName;

	public TitleSearchNotPresentException(String resourceName, String fieldName, String titleName) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName, titleName));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.titleName = titleName;
	}
}
