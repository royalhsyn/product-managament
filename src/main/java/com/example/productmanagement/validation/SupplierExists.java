package com.example.productmanagement.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {SupplierExistsValidator.class}
)
public @interface SupplierExists {
    String message() default "Entity does not exist.";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}