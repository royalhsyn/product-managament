package com.example.productmanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DescriptionValidator implements ConstraintValidator<DescriptionConstraint, String> {
    private int maxLength;

    @Override
    public void initialize(DescriptionConstraint constraintAnnotation) {
        this.maxLength = constraintAnnotation.maxLength();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return value.length() <= maxLength;
    }
}

