package com.booking.booking_jd.repository;
import com.booking.booking_jd.model.Customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Optional<Customer> findByUsernameAndPassword(String username, String password);

}


