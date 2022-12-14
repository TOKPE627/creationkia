package com.javatechie.awselasticbeanstalkexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.javatechie.awselasticbeanstalkexample.domain.OrderItemCustomer;

@Repository
public interface OrderItemCustomerRepository extends JpaRepository<OrderItemCustomer, Long> {
    
}
