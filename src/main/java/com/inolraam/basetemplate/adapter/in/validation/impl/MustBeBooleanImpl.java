package com.inolraam.basetemplate.adapter.in.validation.impl;

import com.inolraam.basetemplate.adapter.in.validation.MustBeBoolean;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MustBeBooleanImpl implements ConstraintValidator<MustBeBoolean, Boolean> {

    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext context) {
        System.out.println("Must be true or false");
        return value != null && (value == false || value == true);
    }
}
