package com.inolraam.basetemplate.common.exception;

import com.inolraam.basetemplate.common.constant.Fields;

import lombok.Getter;

@Getter
public class DuplicatedFieldException extends  RuntimeException{
    private static final String DEFAULT_MSG = "Duplicate value '%s' for field '%s'.";
    private final String fieldName;
    private final String value;

    public DuplicatedFieldException(Fields field, String value) {
        super(String.format(DEFAULT_MSG, value, field.getLabel()));
        this.fieldName = field.getLabel();
        this.value = value;
    }
}
