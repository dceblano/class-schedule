package edu.seait.exception;

import java.io.Serializable;

public class InvalidSubjectException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5101768270857091372L;
	private String message;

	public InvalidSubjectException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
