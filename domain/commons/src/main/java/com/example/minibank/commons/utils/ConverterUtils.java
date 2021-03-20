package com.example.minibank.commons.utils;

import com.example.minibank.commons.exception.NotValidException;
import com.example.minibank.commons.exception.NullValueException;
import org.apache.commons.lang3.StringUtils;

public class ConverterUtils {

    public static long convertToLong(String value) {
        if (StringUtils.isEmpty(value)) {
            throw new NullValueException("Value is required.");
        }

        if (!StringUtils.isNumeric(value)) {
            throw new NotValidException("Invalid value [" + value + "].");
        }

        try {

            return Long.parseLong(value);

        } catch (NumberFormatException nfe) {

            throw new NotValidException("Value is out of range [" + value + "].");
        }
    }


    public static int convertToInt(String value) {
        if (value == null || value.isEmpty()) {
            throw new NullValueException("Value is required.");
        }

        try {

            return Integer.parseInt(value.trim());

        } catch (NumberFormatException nfe) {

            throw new NotValidException("Value is out of range [" + value + "].");
        }
    }
}
