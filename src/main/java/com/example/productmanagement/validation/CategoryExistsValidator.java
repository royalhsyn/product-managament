package com.example.productmanagement.validation;

import com.example.productmanagement.service.EntityExistenceService;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CategoryExistsValidator implements ConstraintValidator<CategoryExists, Long> {
    @Autowired
    private EntityExistenceService entityExistenceService;

    @Override
    public boolean isValid(Long categoryId, ConstraintValidatorContext context) {
        if (categoryId == null) {
            return false;
        }

        return entityExistenceService.doesCategoryExist(categoryId);
    }
}

