package com.example.productmanagement.dto;

import com.example.productmanagement.validation.*;
import lombok.*;


/**
 * DTO for {@link com.example.productmanagement.model.Product}
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
    @NameConstraint
    String name;
    @DescriptionConstraint
    String description;
    @PositiveNumber
    Double price;
    @CategoryExists
    Long categoryId;
    @SupplierExists
    Long supplierId;

}