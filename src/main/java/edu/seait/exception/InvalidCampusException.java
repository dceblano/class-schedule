package edu.seait.exception;

import java.io.Serializable;

public class InvalidCampusException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -554270631107992854L;
	private String message;

	public InvalidCampusException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
