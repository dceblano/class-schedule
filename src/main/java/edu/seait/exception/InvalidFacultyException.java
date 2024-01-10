package edu.seait.exception;

import java.io.Serializable;

public class InvalidFacultyException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8112734417792005476L;
	private String message;

	public InvalidFacultyException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
