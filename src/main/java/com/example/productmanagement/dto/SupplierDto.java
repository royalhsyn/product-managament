package com.example.productmanagement.dto;

import com.example.productmanagement.validation.NameConstraint;
import jakarta.validation.constraints.Size;
import lombok.*;


/**
 * DTO for {@link com.example.productmanagement.model.Supplier}
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupplierDto {
    @NameConstraint
    String name;
    String address;
}