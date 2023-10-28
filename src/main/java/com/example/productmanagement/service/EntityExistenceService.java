package com.example.productmanagement.service;

import com.example.productmanagement.repository.CategoryRepository;
import com.example.productmanagement.repository.SupplierRepository;
import org.springframework.stereotype.Service;

@Service
public class EntityExistenceService {

    private final CategoryRepository categoryRepository;

    private final SupplierRepository supplierRepository;

    public EntityExistenceService(CategoryRepository categoryRepository, SupplierRepository supplierRepository) {
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }

    public boolean doesCategoryExist(Long categoryId) {
        return categoryRepository.existsById(categoryId);
    }

    public boolean doesSupplierExist(Long supplierId) {
        return supplierRepository.existsById(supplierId);
    }
}
