package com.example.minibank.commons.exception;

@SuppressWarnings("serial")
public class NotFoundException extends ApiRequestException {

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message) {
		super(message + "");
	}
}
