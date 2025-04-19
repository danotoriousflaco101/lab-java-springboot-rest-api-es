package com.example.LaboSpringbootRESTAPI.service;

import com.example.LaboSpringbootRESTAPI.exception.CustomerNotFoundException;
import com.example.LaboSpringbootRESTAPI.model.Customer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {

    private final Map<String, Customer> customerMap = new HashMap<>();

    public void addCustomer(Customer customer) {
        customerMap.put(customer.getEmail(), customer);
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    public Customer getCustomerByEmail(String email) {
        Customer customer = customerMap.get(email);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with email '" + email + "' is not found.");
        }
        return customer;
    }

    public void updateCustomer(String email, Customer updatedCustomer) {
        if (!customerMap.containsKey(email)) {
            throw new CustomerNotFoundException("Can't update: customer with email '" + email + "' is not found.");
        }
        customerMap.put(email, updatedCustomer);
    }

    public void deleteCustomer(String email) {
        if (!customerMap.containsKey(email)) {
            throw new CustomerNotFoundException("Can't delete: customer with email '" + email + "' is not found.");
        }
        customerMap.remove(email);
    }
}