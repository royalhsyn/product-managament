package com.example.productmanagement.controller;

import com.example.productmanagement.baseResponse.BaseResponse;
import com.example.productmanagement.dto.CategoryDto;
import com.example.productmanagement.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    @PostMapping
    public BaseResponse<Object> save(@Valid @RequestBody CategoryDto categoryDto){
        categoryService.save(categoryDto);
        return BaseResponse.succes(null);
    }

    @GetMapping
    public List<CategoryDto> list(){
        return categoryService.list();
    }
}
