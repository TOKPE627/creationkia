package com.javatechie.awselasticbeanstalkexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.awselasticbeanstalkexample.domain.OrderCustomer;

public interface OrderCustomerRepository extends JpaRepository<OrderCustomer, Long>{
    OrderCustomer findByOrderTrackingNumber(String orderTrackingNumber);
}