package com.example.productmanagement.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.productmanagement.dto.SupplierDto;
import com.example.productmanagement.mapstruct.SupplierMapper;
import com.example.productmanagement.model.Supplier;
import com.example.productmanagement.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SupplierServiceTest {
    private SupplierService supplierService;
    private SupplierMapper supplierMapper;
    private SupplierRepository supplierRepository;

    @BeforeEach
    public void setUp() {
        supplierMapper = Mockito.mock(SupplierMapper.class);
        supplierRepository = Mockito.mock(SupplierRepository.class);
        supplierService = new SupplierService(supplierRepository, supplierMapper);
    }

    @Test
    public void testSave() {
        SupplierDto supplierDto = new SupplierDto();

        Supplier entity = new Supplier();
        Mockito.when(supplierMapper.toEntity(supplierDto)).thenReturn(entity);
        Mockito.when(supplierRepository.save(entity)).thenReturn(entity);
        Mockito.when(supplierMapper.toDto(entity)).thenReturn(supplierDto);

        supplierService.save(supplierDto);

        Mockito.verify(supplierMapper).toEntity(supplierDto);
        Mockito.verify(supplierRepository).save(entity);
        Mockito.verify(supplierMapper).toDto(entity);
    }
}
