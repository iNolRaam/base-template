package com.inolraam.basetemplate.common.exception;


import com.inolraam.basetemplate.common.exception.dto.InvalidFieldsDto;
import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class RequestValidationException extends  RuntimeException{
    private static final String DEFAULT_MSG = "Invalid fields in the request.";
    private final Object[] invalidFields;
    private final boolean isManual;

    public RequestValidationException(BindingResult bindingResult) {
        super(DEFAULT_MSG);
        this.invalidFields = getErrors(bindingResult);
        this.isManual = false;
    }

    public RequestValidationException(InvalidFieldsDto invalidField) {
        super(DEFAULT_MSG);
        this.invalidFields = getErrors(invalidField);
        this.isManual = true;
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

    private static Object[] getErrors(InvalidFieldsDto invalidField) {
        final List<Map.Entry<String, String>> errors = new ArrayList<>();
        errors.add(Map.entry(invalidField.getField().getLabel(), invalidField.getMessage()));

        return errors.toArray();
    }

}
