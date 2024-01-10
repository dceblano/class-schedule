package edu.seait.exception;

import java.io.Serializable;

public class InvalidSchoolYearException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -569139975280834320L;
	private String message;

	public InvalidSchoolYearException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
