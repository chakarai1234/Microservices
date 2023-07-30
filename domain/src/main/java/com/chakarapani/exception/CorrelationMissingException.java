package com.chakarapani.exception;

public class CorrelationMissingException extends RuntimeException {


	public CorrelationMissingException() {
		super();
	}

	public CorrelationMissingException(String message) {
		super(message);
	}

	public CorrelationMissingException(String message, Throwable cause) {
		super(message, cause);
	}

}
