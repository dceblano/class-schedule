package edu.seait.exception;

import java.io.Serializable;

public class InvalidRoomException extends Exception implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -119573787965079195L;
	private String message;
	
	public InvalidRoomException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
