package com.example.productmanagement.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {DescriptionValidator.class}
)
public @interface DescriptionConstraint {
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
    int maxLength() default 255;
    String message() default "Description should not exceed {maxLength} characters.";
}

