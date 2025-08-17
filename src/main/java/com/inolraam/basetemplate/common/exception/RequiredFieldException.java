package com.inolraam.basetemplate.common.exception;

import lombok.Getter;

@Getter
public class RequiredFieldException extends RuntimeException {
    private static final String DEFAULT_MSG = "Required field: %s.";
    private String fieldName;

    public RequiredFieldException(String fieldName) {
        super(String.format(DEFAULT_MSG, fieldName));
        this.fieldName = fieldName;
    }
}
