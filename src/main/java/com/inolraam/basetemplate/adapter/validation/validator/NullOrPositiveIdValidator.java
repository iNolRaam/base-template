package com.inolraam.basetemplate.adapter.validation.validator;

import com.inolraam.basetemplate.adapter.validation.NullOrPositiveId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullOrPositiveIdValidator implements ConstraintValidator<NullOrPositiveId, Long> {

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value == null || value > 0;
    }
}
