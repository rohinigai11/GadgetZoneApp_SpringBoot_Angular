package com.gadgetzone.controller;

import com.gadgetzone.entity.Product;
import com.gadgetzone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    // CREATE
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product saved = productService.saveProduct(product);
        return ResponseEntity.ok(saved);
    }

    // READ ALL
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Product> getOne(@PathVariable Long id) {
        Optional<Product> opt = productService.getProductById(id);
        return opt.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                 @RequestBody Product update) {
        Optional<Product> opt = productService.getProductById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();
        Product p = opt.get();
        // copy fields
        p.setName(update.getName());
        p.setBrand(update.getBrand());
        p.setCategory(update.getCategory());
        p.setDescription(update.getDescription());
        p.setPrice(update.getPrice());
        p.setStockQuantity(update.getStockQuantity());
        p.setImageUrl(update.getImageUrl());
        return ResponseEntity.ok(productService.saveProduct(p));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
