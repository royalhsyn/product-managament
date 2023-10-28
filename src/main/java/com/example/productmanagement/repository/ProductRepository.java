package com.example.productmanagement.repository;

import com.example.productmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}