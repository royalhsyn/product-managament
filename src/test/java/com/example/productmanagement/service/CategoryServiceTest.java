package com.example.productmanagement.service;

import com.example.productmanagement.dto.CategoryDto;
import com.example.productmanagement.mapstruct.CategoryMapper;
import com.example.productmanagement.model.Category;
import com.example.productmanagement.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository repo;

    @Mock
    private CategoryMapper categoryMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveCategory() {
        CategoryDto categoryDto = new CategoryDto();
        Category category = new Category();

        when(categoryMapper.toEntity(categoryDto)).thenReturn(category);
        when(repo.save(category)).thenReturn(category);
        when(categoryMapper.toDto(category)).thenReturn(categoryDto);

        categoryService.save(categoryDto);

        verify(categoryMapper).toEntity(categoryDto);
        verify(repo).save(category);
        verify(categoryMapper).toDto(category);
    }

    @Test
    public void testSaveCategoryThrowsException() {
        CategoryDto categoryDto = new CategoryDto();

        when(categoryMapper.toEntity(categoryDto)).thenReturn(new Category()); // entity
        when(repo.save(any())).thenThrow(new RuntimeException("Simulated exception"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> categoryService.save(categoryDto));

        assertEquals("Simulated exception", exception.getMessage());
    }


    private List<Category> createSampleCategoryList() {
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Category 1");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Category 2");

        return List.of(category1, category2);
    }

    @Test
    public void testListCategories() {
        List<Category> sampleCategories = createSampleCategoryList();

        when(repo.findAll()).thenReturn(sampleCategories);

        List<CategoryDto> expectedCategoryDtos = sampleCategories.stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());

        List<CategoryDto> actualCategoryDtos = categoryService.list();
        assertEquals(expectedCategoryDtos, actualCategoryDtos);
    }

}


