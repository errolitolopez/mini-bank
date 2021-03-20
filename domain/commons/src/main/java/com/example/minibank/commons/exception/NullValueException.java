package com.example.minibank.commons.exception;

@SuppressWarnings("serial")
public class NullValueException extends ApiRequestException {

	public NullValueException(String message, Throwable cause) {
		super(message, cause);
	}

	public NullValueException(String message) {
		super(message);
	}
}
