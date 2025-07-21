package com.booking.booking_jd.controller;

import com.booking.booking_jd.model.Customer;
import com.booking.booking_jd.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	@Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        return customerRepository.findById(id)
            .map(customer -> {
                customer.setName(updatedCustomer.getName());
                customer.setEmail(updatedCustomer.getEmail());
                customer.setUsername(updatedCustomer.getUsername());
                customer.setPassword(updatedCustomer.getPassword());
                customer.setTypeUser(updatedCustomer.getTypeUser());
                // No sobreescribimos bookings aquí por precaución
                return ResponseEntity.ok(customerRepository.save(customer));
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        return customerRepository.findById(id)
            .<ResponseEntity<Void>>map(customer -> {
                customerRepository.delete(customer);
                return ResponseEntity.noContent().build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/login")
    public ResponseEntity<Customer> login(@RequestBody Customer loginData) {
        Optional<Customer> customerOpt = customerRepository.findByUsernameAndPassword(
            loginData.getUsername(), loginData.getPassword()
        );

        return customerOpt
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}