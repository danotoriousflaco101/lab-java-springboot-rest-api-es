package com.example.LaboSpringbootRESTAPI.controller;

import com.example.LaboSpringbootRESTAPI.model.Customer;
import com.example.LaboSpringbootRESTAPI.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<String> addCustomer(@Valid @RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer created correctly.");
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        return ResponseEntity.ok(customerService.getCustomerByEmail(email));
    }

    @PutMapping("/{email}")
    public ResponseEntity<String> updateCustomer(@PathVariable String email,
                                                 @Valid @RequestBody Customer updatedCustomer) {
        customerService.updateCustomer(email, updatedCustomer);
        return ResponseEntity.ok("Customer updated correctly.");
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String email) {
        customerService.deleteCustomer(email);
        return ResponseEntity.ok("Customer deleted.");
    }
}