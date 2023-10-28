package com.example.productmanagement.service;

import com.example.productmanagement.dto.CategoryDto;
import com.example.productmanagement.mapstruct.CategoryMapper;
import com.example.productmanagement.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repo;

    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository repo, CategoryMapper categoryMapper) {
        this.repo = repo;
        this.categoryMapper = categoryMapper;
    }

    @Transactional(rollbackFor = SQLException.class)
    public void save(CategoryDto categoryDto){
        Optional.of(categoryDto)
                .map(categoryMapper::toEntity)
                .map(repo::save)
                .map(categoryMapper::toDto)
                .orElseThrow();
    }

    public List<CategoryDto> list(){
        return repo.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }
}
