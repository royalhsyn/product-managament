package com.example.productmanagement.service;

import com.example.productmanagement.dto.ProductDto;
import com.example.productmanagement.exception.NotFoundException;
import com.example.productmanagement.mapstruct.ProductMapper;
import com.example.productmanagement.model.Category;
import com.example.productmanagement.model.Product;
import com.example.productmanagement.model.Supplier;
import com.example.productmanagement.repository.CategoryRepository;
import com.example.productmanagement.repository.ProductRepository;
import com.example.productmanagement.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private ProductMapper productMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveProductWithCategoryAndSupplier() {

        ProductDto productDto = new ProductDto();
        productDto.setCategoryId(1L);
        productDto.setSupplierId(1L);

        Product product = new Product();
        Category category = new Category();
        Supplier supplier = new Supplier();

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
        when(productMapper.toEntity(productDto)).thenReturn(product);

        ProductDto savedProductDto = productService.save(productDto);

        verify(productRepository).save(product);

        assertEquals(productDto, savedProductDto);
    }

    @Test
    public void testSaveProductWithoutCategoryOrSupplier() {
        ProductDto productDto = new ProductDto();
        productDto.setCategoryId(1L);
        productDto.setSupplierId(1L);

        when(productRepository.save(any(Product.class))).thenReturn(null);

        ProductDto savedProductDto = productService.save(productDto);

        verify(productRepository, never()).save(any(Product.class));

        assertEquals(productDto, savedProductDto);
    }

    @Test
    public void testUpdateProduct() {
        Long productId = 1L;
        ProductDto productDto = new ProductDto();
        productDto.setCategoryId(1L);
        productDto.setSupplierId(1L);
        Product product = new Product();
        product.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(categoryRepository.findById(productDto.getCategoryId())).thenReturn(Optional.of(new Category()));
        when(supplierRepository.findById(productDto.getSupplierId())).thenReturn(Optional.of(new Supplier()));
        when(productMapper.toEntity(productDto)).thenReturn(product);

        ProductDto updatedProductDto = productService.update(productId, productDto);

        verify(productRepository).save(product);

        assertEquals(productDto, updatedProductDto);
    }



    private List<Product> createSampleProductList() {

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");

        return List.of(product1, product2);
    }

@Test
    public void testListProducts() {
        List<Product> sampleProducts = createSampleProductList();

        when(productRepository.findAll()).thenReturn(sampleProducts);

        List<ProductDto> expectedProductDtos = sampleProducts.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());

        List<ProductDto> actualProductDtos = productService.list();

        assertEquals(expectedProductDtos, actualProductDtos);
    }


    @Test
    public void testFindExistingProduct() {
        Long productId = 1L;
        Product sampleProduct = new Product();
        sampleProduct.setId(productId);
        ProductDto sampleProductDto = new ProductDto();

        when(productRepository.findById(productId)).thenReturn(Optional.of(sampleProduct));

        when(productMapper.toDto(sampleProduct)).thenReturn(sampleProductDto);

        ProductDto foundProductDto = productService.findById(productId);

        assertEquals(sampleProductDto, foundProductDto);
    }

    @Test
    public void testFindNonexistentProduct() {
        Long nonExistentProductId = 100L;

        when(productRepository.findById(nonExistentProductId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> productService.findById(nonExistentProductId));
    }


    @Test
    public void testDeleteExistingProduct() {
        Long productId = 1L;

        when(productRepository.existsById(productId)).thenReturn(true);

        productService.delete(productId);

        verify(productRepository).deleteById(productId);
    }

    @Test
    public void testDeleteNonexistentProduct() {
        Long nonExistentProductId = 100L;

        when(productRepository.existsById(nonExistentProductId)).thenReturn(false);

        productService.delete(nonExistentProductId);

        verify(productRepository, never()).deleteById(any());
    }

    @Test
    public void testDeleteThrowsRuntimeException() {
        Long productId = 1L;

        when(productRepository.existsById(productId)).thenReturn(true);

        doThrow(new RuntimeException("Delete zamani xeta bas verdi")).when(productRepository).deleteById(productId);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> productService.delete(productId));

        assertEquals("Delete zamani xeta bas verdi", exception.getMessage());
    }
}
