package com.example.LaboSpringbootRESTAPI.model;

import jakarta.validation.constraints.*;

public class Product {

    @NotBlank(message = "Name is mandatory.")
    @Size(min = 2, message = "Name MUST have at least 2 (two) chars.")
    private String name;

    @Positive(message = "Price must be positive.")
    private double price;

    @NotBlank(message = "Category is mandatory.")
    private String category;

    @PositiveOrZero(message = "Quantity can't be less or equal to 0 (zero).")
    private int quantity;


    public Product() {}

    // Constructor
    public Product(String name, double price, String category, int quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    // Getter + Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}