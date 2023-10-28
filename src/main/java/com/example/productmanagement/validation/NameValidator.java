package com.example.productmanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<NameConstraint, String> {

    private int minLength;
    private int maxLength;

    @Override
    public void initialize(NameConstraint constraintAnnotation) {
        this.minLength = constraintAnnotation.minLength();
        this.maxLength = constraintAnnotation.maxLength();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }


        int length = value.length();
        return length >= minLength && length <= maxLength;
    }
}

