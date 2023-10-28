package com.example.productmanagement.controller;

import com.example.productmanagement.baseResponse.BaseResponse;
import com.example.productmanagement.dto.SupplierDto;
import com.example.productmanagement.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }


    @PostMapping
    public BaseResponse<Object> save(@Valid @RequestBody SupplierDto supplierDto){
        supplierService.save(supplierDto);
        return BaseResponse.succes(null);
    }

}
