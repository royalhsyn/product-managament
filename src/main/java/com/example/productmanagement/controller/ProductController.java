package com.example.productmanagement.controller;

import com.example.productmanagement.baseResponse.BaseResponse;
import com.example.productmanagement.dto.ProductDto;
import com.example.productmanagement.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/product")
public class  ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @PostMapping
    public BaseResponse<Object> save(@Valid @RequestBody ProductDto productDto){
        ProductDto product = productService.save(productDto);
        return BaseResponse.succes(product);
    }
    @PutMapping("/{id}")
    public BaseResponse<Object> update(@PathVariable(name = "id") Long id,@Valid @RequestBody ProductDto productDto){
        ProductDto product = productService.update(id, productDto);
        return BaseResponse.builder().message("Product updated succesfully!").status(HttpStatus.OK).data(product).build();
    }

    @GetMapping
    public List<ProductDto> list(){
        return productService.list();
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable(name = "id") Long id){
        return productService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id){
        productService.delete(id);
    }
}
