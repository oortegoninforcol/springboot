package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class MainController {

    @Autowired
    private ProductService productService;

    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @PostMapping("/products")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        // Logic to save the product to the database
        Product savedProduct = productService.createProduct(product);
        return ResponseEntity.ok(savedProduct);
    }
    

}
