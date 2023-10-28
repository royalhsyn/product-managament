package com.example.productmanagement.validation;

import com.example.productmanagement.service.EntityExistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SupplierExistsValidator implements ConstraintValidator<SupplierExists, Long> {

    @Autowired
    private EntityExistenceService entityExistenceService;

    @Override
    public boolean isValid(Long supplierId, ConstraintValidatorContext context) {
        if (supplierId == null) {
            return false;
        }

        return entityExistenceService.doesSupplierExist(supplierId);
    }
}
