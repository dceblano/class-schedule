package edu.seait.exception;

import java.io.Serializable;

public class InvalidScheduleException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5009009161023754977L;
	private String message;

	public InvalidScheduleException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
