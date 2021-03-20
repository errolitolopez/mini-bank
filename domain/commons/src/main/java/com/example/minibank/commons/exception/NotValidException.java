package com.example.minibank.commons.exception;

@SuppressWarnings("serial")
public class NotValidException extends ApiRequestException {

	public NotValidException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotValidException(String message) {
		super(message);
	}
}
