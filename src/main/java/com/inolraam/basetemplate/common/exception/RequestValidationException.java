package com.inolraam.basetemplate.common.exception;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

@Getter
public class RequestValidationException extends  RuntimeException{
    private static final String DEFAULT_MSG = "Invalid fields in the request.";
    private Map<String, String> invalidFields;

    public RequestValidationException(BindingResult bindingResult) {
        super(DEFAULT_MSG);
        this.invalidFields = getErrors(bindingResult);
    }

    private static Map<String, String> getErrors(BindingResult bindingResult) {
        final Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach((error) -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;
    }
}
