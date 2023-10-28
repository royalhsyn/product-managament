package com.example.productmanagement.mapstruct;

import com.example.productmanagement.dto.ProductDto;
import com.example.productmanagement.model.Category;
import com.example.productmanagement.model.Product;
import com.example.productmanagement.model.Supplier;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    Product toEntity(ProductDto productDto);

    @Mapping(target = "categoryId", expression = "java(toSet(product.getCategory()))")
    @Mapping(target = "supplierId", expression = "java(toSetSupplier(product.getSupplier()))")
    ProductDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductDto productDto, @MappingTarget Product product);

    default Long toSet(Category category) {
        return category.getId();
    }

    default Long toSetSupplier(Supplier supplier) {
        return supplier.getId();
    }
}