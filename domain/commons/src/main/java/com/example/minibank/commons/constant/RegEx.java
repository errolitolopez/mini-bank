package com.example.minibank.commons.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RegEx {

    EMAIL("^(?!.*?\\.\\.)([\\d\\w_\\.\\+]+)@([\\d\\w_\\.]+)\\.([\\d\\w]{1,})$"),
    SPECIAL_CHARACTER("[^A-z\\s\\d][\\\\\\^]?"),
    NUMERIC("[0-9]"),
    ALPHA("[A-z]"),
    NON_NUMERIC("[^\\\\0-9]"),
    NON_ALPHA("[^a-zA-Z]");

    private final String pattern;
}