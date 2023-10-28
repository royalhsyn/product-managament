package com.example.productmanagement.dto;

import com.example.productmanagement.validation.NameConstraint;
import lombok.*;


/**
 * DTO for {@link com.example.productmanagement.model.Category}
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {
    @NameConstraint
    String name;
}