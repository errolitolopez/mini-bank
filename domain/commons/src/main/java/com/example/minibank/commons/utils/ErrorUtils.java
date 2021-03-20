package com.example.minibank.commons.utils;

import com.example.minibank.commons.exception.NotValidException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.Optional;

public class ErrorUtils {
    public static void throwFormValidationErrors(Errors errors) {
        final Optional<ObjectError> objectErrorOptional = errors.getAllErrors()
                .stream()
                .findFirst();

        if (objectErrorOptional.isPresent()) {
            final String defaultMessage = objectErrorOptional.get().getDefaultMessage();
            throw new NotValidException(defaultMessage);
        }
    }
}
