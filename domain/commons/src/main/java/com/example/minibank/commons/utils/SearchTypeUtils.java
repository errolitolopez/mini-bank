package com.example.minibank.commons.utils;

import com.example.minibank.commons.constant.SearchType;
import com.example.minibank.commons.exception.NotValidException;
import com.example.minibank.commons.exception.NullValueException;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

import static com.example.minibank.commons.constant.RegEx.NUMERIC;
import static com.example.minibank.commons.constant.SearchType.EMAIL;
import static com.example.minibank.commons.constant.SearchType.ID;
import static com.example.minibank.commons.constant.SearchType.NAME;
import static com.example.minibank.commons.constant.SearchType.REFERENCE_NO;
import static com.example.minibank.commons.constant.SearchType.values;

public class SearchTypeUtils {
    public static Optional<SearchType> getType(String value) {
        int type;

        if (StringUtils.isEmpty(value)) {
            return Optional.empty();
        }

        if (value.matches(NUMERIC.getPattern())) {
            try {
                type = ConverterUtils.convertToInt(value);
                if (type == 1) {
                    return Optional.of(ID);
                }

                if (type == 2) {
                    return Optional.of(REFERENCE_NO);
                }

                if (type == 3) {
                    return Optional.of(EMAIL);

                }

                if (type == 4) {
                    return Optional.of(NAME);
                }

            } catch (NullValueException | NotValidException e) {
                return Optional.empty();
            }
        }

        return Arrays.stream(values())
                .filter(o -> o.name().equalsIgnoreCase(value))
                .findFirst();
    }
}
