package com.example.productmanagement.mapstruct;

import com.example.productmanagement.dto.SupplierDto;
import com.example.productmanagement.model.Supplier;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SupplierMapper {
    Supplier toEntity(SupplierDto supplierDto);

    SupplierDto toDto(Supplier supplier);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Supplier partialUpdate(SupplierDto supplierDto, @MappingTarget Supplier supplier);
}