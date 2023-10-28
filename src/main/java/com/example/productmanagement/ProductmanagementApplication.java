package com.example.productmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class ProductmanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductmanagementApplication.class, args);
    }

}
