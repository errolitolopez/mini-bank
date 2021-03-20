package com.example.minibank.app.error;

import com.example.minibank.app.error.response.ApiExceptionResponse;
import com.example.minibank.commons.exception.ApiRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionControllerAdvice {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {

        HttpStatus badRequest = HttpStatus.UNPROCESSABLE_ENTITY;
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                "Transaction Failed. " + e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("+08:00")));

        return new ResponseEntity<>(apiExceptionResponse, badRequest);
    }
}
