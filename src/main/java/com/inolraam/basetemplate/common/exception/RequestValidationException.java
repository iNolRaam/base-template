package com.inolraam.basetemplate.common.exception;


import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class RequestValidationException extends  RuntimeException{
    private static final String DEFAULT_MSG = "Invalid fields in the request.";
    private final Object[] invalidFields;

    public RequestValidationException(BindingResult bindingResult) {
        super(DEFAULT_MSG);
        this.invalidFields = getErrors(bindingResult);
    }

    private static Object[] getErrors(BindingResult bindingResult) {
        final List<Map.Entry<String, String>> errors = new ArrayList<>();
        bindingResult.getFieldErrors().forEach((error) -> {
            if(error.getDefaultMessage() != null) {
                errors.add(Map.entry(error.getField(), error.getDefaultMessage()));
            }
        });
        return errors.toArray();
    }

}
