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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repo;

    private final CategoryRepository categoryRepository;

    private final SupplierRepository supplierRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository repo, CategoryRepository categoryRepository, SupplierRepository supplierRepository, ProductMapper productMapper) {
        this.repo = repo;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
        this.productMapper = productMapper;
    }

    public ProductDto save(ProductDto productDto){
        Product product=productMapper.toEntity(productDto);
        Optional<Category> category=categoryRepository.findById(productDto.getCategoryId());
        Optional<Supplier> supplier=supplierRepository.findById(productDto.getSupplierId());
        if (category.isPresent() && supplier.isPresent()){
            product.setCategory(category.get());
            product.setSupplier(supplier.get());
        }
        repo.save(product);
        return productDto;
    }

    public ProductDto update(Long id,ProductDto productDto){
        Optional<Product> ent=repo.findById(id);
        Product product=null;
        if (ent.isPresent()){
            product=ent.get();
        }
        product=productMapper.toEntity(productDto);
        product.setId(id);
        Optional<Category> category=categoryRepository.findById(productDto.getCategoryId());
        Optional<Supplier> supplier=supplierRepository.findById(productDto.getSupplierId());
        if (category.isPresent() && supplier.isPresent()){
            product.setCategory(category.get());
            product.setSupplier(supplier.get());
        }
        repo.save(product);
        return productDto;
    }

    public List<ProductDto> list(){
        return repo.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    public ProductDto findById(Long id){
        return repo.findById(id)
        .map(productMapper::toDto)
                .orElseThrow(()->new NotFoundException("Bele bir id li user yoxdur"));
    }

    @Transactional(rollbackFor = SQLException.class)
    public void delete(Long id) {
        if (repo.existsById(id)) {
            try {
                repo.deleteById(id);

            } catch (RuntimeException ex) {
                throw new RuntimeException("Delete zamani xeta bas verdi");
            }
        }
    }
}
