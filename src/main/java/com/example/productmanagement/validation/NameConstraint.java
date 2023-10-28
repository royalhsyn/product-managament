package com.example.productmanagement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {NameValidator.class}
)
@Documented
public @interface NameConstraint {

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int minLength() default 3;
    int maxLength() default 50;
    String message() default "Invalid name. Name should be between {minLength} and {maxLength} characters.";
}

