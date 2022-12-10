package com.example.finalexam.repositories;

import com.example.finalexam.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findCustomerById (long kw);
}
