package edu.seait.exception;

import java.io.Serializable;

public class InvalidClassException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6143755480674793257L;
	private String message;

	public InvalidClassException(String msg) {
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
