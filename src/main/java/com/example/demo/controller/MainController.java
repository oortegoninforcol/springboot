package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
    // READ (todos) → GET /api/productos
    @GetMapping("/products")
    public ResponseEntity<List<Product>> listProducts() {
        return ResponseEntity.ok(productService.listProducts()); // 200
    }

    // READ (uno) → GET /api/productos/{id}
    @GetMapping("products/{id}")
    public ResponseEntity<Product> obtenerPorId(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok) // si existe, 200
                .orElse(ResponseEntity.notFound().build()); // si no, 404
    }

    // UPDATE → PUT /api/productos/{id}
    @PutMapping("products/{id}")
    public ResponseEntity<Product> actualizar(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct); // 200
    }

    // DELETE → DELETE /api/productos/{id}
    @DeleteMapping("products/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productService.eliminarProducto(id);
        return ResponseEntity.noContent().build(); // 204
    }

}
