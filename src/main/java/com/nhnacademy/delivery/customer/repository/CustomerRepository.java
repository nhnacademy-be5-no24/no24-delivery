package com.nhnacademy.delivery.customer.repository;

import com.nhnacademy.delivery.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
