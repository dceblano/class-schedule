package edu.seait.exception;

import java.io.Serializable;

public class InvalidDepartmentException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7550903353990398534L;
	private String message;

	public InvalidDepartmentException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
