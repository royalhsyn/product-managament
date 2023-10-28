package com.example.productmanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PositiveNumberValidator implements ConstraintValidator<PositiveNumber, Number> {
    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return value.doubleValue() > 0;
    }
}

