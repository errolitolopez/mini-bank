package com.example.minibank.commons.exception;

@SuppressWarnings("serial")
public class NotAllowedException extends ApiRequestException {

	public NotAllowedException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotAllowedException(String message) {
		super(message);
	}
}
