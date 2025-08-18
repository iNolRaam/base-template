package com.inolraam.basetemplate.common.exception;

import lombok.Getter;

@Getter
public class DuplicatedFieldException extends  RuntimeException{
    private static final String DEFAULT_MSG = "Duplicate value '%s' for field '%s'.";
    private final String fieldName;
    private final String value;

    public DuplicatedFieldException(String fieldName, String value) {
        super(String.format(DEFAULT_MSG, value, fieldName));
        this.fieldName = fieldName;
        this.value = value;
    }
}
