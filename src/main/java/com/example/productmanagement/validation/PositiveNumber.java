package com.example.productmanagement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {PositiveNumberValidator.class}
)
public @interface PositiveNumber {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String message() default "Price should be a positive number.";
}

