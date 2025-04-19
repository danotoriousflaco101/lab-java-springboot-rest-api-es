package com.example.LaboSpringbootRESTAPI.service;

import com.example.LaboSpringbootRESTAPI.exception.ProductNotFoundException;
import com.example.LaboSpringbootRESTAPI.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final Map<String, Product> productMap = new HashMap<>();

    public void addProduct(Product product) {
        productMap.put(product.getName(), product);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }

    public Product getProductByName(String name) {
        Product product = productMap.get(name);
        if (product == null) {
            throw new ProductNotFoundException("Product '" + name + "' not found.");
        }
        return product;
    }

    public void updateProduct(String name, Product updatedProduct) {
        if (!productMap.containsKey(name)) {
            throw new ProductNotFoundException("Can't update: product '" + name + "' not found.");
        }
        productMap.put(name, updatedProduct);
    }

    public void deleteProduct(String name) {
        if (!productMap.containsKey(name)) {
            throw new ProductNotFoundException("Can't delete: product '" + name + "' not found.");
        }
        productMap.remove(name);
    }

    public List<Product> getProductsByCategory(String category) {
        return productMap.values().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByPriceRange(double min, double max) {
        return productMap.values().stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }
}