package com.rest.carrental.controller;

import com.rest.carrental.repository.CustomerRepo;
import com.rest.carrental.entity.Customer;
import com.rest.carrental.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerRepo repo;

    @GetMapping("/customers")
    List<Customer> all() {
        return repo.findAll();
    }

    @GetMapping("/customers/{id}")
    Customer one(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @PostMapping("/customers")
    Customer add(@RequestBody Customer customer) {
        return repo.save(customer);
    }

    @PutMapping("/customers/{id}")
    Customer update(@RequestBody Customer newCustomer, @PathVariable Long id) {
        return repo.findById(id)
                .map(customer -> {
                    customer.setName(newCustomer.getName());
                    return repo.save(customer);
                })
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @DeleteMapping("/customers/{id}")
    void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
