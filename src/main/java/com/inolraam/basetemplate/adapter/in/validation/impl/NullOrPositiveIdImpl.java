package com.inolraam.basetemplate.adapter.in.validation.impl;

import com.inolraam.basetemplate.adapter.in.validation.NullOrPositiveId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullOrPositiveIdImpl implements ConstraintValidator<NullOrPositiveId, Long> {

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value == null || value > 0;
    }
}
