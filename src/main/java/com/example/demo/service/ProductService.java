package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // READ (todos)
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    // READ (uno por id)
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // UPDATE
    public Product updateProduct(Long id, Product newProductData) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        product.setName(newProductData.getName());
        product.setDescription(newProductData.getDescription());
        product.setPrice(newProductData.getPrice());

        return productRepository.save(product);
    }

    // DELETE
    public void eliminarProducto(Long id) {
        productRepository.deleteById(id);
    }

}
