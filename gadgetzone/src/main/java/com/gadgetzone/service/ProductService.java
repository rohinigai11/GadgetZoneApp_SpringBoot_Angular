package com.gadgetzone.service;

import com.gadgetzone.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product); // Save a product
    List<Product> getAllProducts(); // Get all products
    Optional<Product> getProductById(Long id); // Get product by ID
    void deleteProduct(Long id); // Delete a product
}
