package com.example.productmanagement.service;

import com.example.productmanagement.dto.SupplierDto;
import com.example.productmanagement.mapstruct.SupplierMapper;
import com.example.productmanagement.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupplierRepository repo;

    private final SupplierMapper supplierMapper;

    public SupplierService(SupplierRepository repo, SupplierMapper supplierMapper) {
        this.repo = repo;
        this.supplierMapper = supplierMapper;
    }

    @Transactional(rollbackFor = SQLException.class)
    public void save(SupplierDto supplierDto){
        Optional.of(supplierDto)
                .map(supplierMapper::toEntity)
                .map(repo::save)
                .map(supplierMapper::toDto)
                .orElseThrow();
    }



}
