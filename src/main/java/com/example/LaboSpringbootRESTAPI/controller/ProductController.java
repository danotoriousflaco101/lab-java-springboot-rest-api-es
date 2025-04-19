package com.example.LaboSpringbootRESTAPI.controller;

import com.example.LaboSpringbootRESTAPI.exception.MissingApiKeyException;
import com.example.LaboSpringbootRESTAPI.model.Product;
import com.example.LaboSpringbootRESTAPI.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private void checkApiKey(String apiKey) {
        if (!"123456".equals(apiKey)) {
            throw new MissingApiKeyException(apiKey == null ? "API-Key is missing." : "API-Key is invalid.");
        }
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                             @Valid @RequestBody Product product) {
        checkApiKey(apiKey);
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product listed correctly.");
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestHeader(value = "API-Key", required = false) String apiKey) {
        checkApiKey(apiKey);
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                                    @PathVariable String name) {
        checkApiKey(apiKey);
        Product product = productService.getProductByName(name);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{name}")
    public ResponseEntity<String> updateProduct(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                                @PathVariable String name,
                                                @Valid @RequestBody Product product) {
        checkApiKey(apiKey);
        productService.updateProduct(name, product);
        return ResponseEntity.ok("Product updated.");
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteProduct(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                                @PathVariable String name) {
        checkApiKey(apiKey);
        productService.deleteProduct(name);
        return ResponseEntity.ok("Product deleted.");
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                                               @PathVariable String category) {
        checkApiKey(apiKey);
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @GetMapping("/price")
    public ResponseEntity<List<Product>> getProductsByPriceRange(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                                                 @RequestParam double min,
                                                                 @RequestParam double max) {
        checkApiKey(apiKey);
        if (min < 0 || max < 0 || min > max) {
            throw new IllegalArgumentException("Invalid price range.");
        }
        return ResponseEntity.ok(productService.getProductsByPriceRange(min, max));
    }
}