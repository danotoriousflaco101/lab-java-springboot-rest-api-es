package com.example.LaboSpringbootRESTAPI.model;

import jakarta.validation.constraints.*;

public class Customer {

    @NotBlank(message = "Name is mandatory.")
    private String name;

    @Email(message = "Invalid email or not found.")
    @NotBlank(message = "E-mail is mandatory.")
    private String email;

    @Min(value = 18, message = "You MUST be at least +18.")
    private int age;

    @NotBlank(message = "Address is mandatory.")
    private String address;

    // Constructor
    public Customer() {}

    public Customer(String name, String email, int age, String address) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    // Getters + Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}