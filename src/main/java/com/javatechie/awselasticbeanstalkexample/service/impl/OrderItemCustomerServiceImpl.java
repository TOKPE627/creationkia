package com.javatechie.awselasticbeanstalkexample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.OrderItemCustomer;
import com.javatechie.awselasticbeanstalkexample.repository.OrderItemCustomerRepository;
import com.javatechie.awselasticbeanstalkexample.service.OrderItemCustomerService;

@Service
public class OrderItemCustomerServiceImpl implements OrderItemCustomerService{

    @Autowired
    private OrderItemCustomerRepository orderItemCustomerRepository;
    
    @Override
    public OrderItemCustomer add(OrderItemCustomer orderItemCustomer) {
       return orderItemCustomerRepository.save(orderItemCustomer);
    }

    @Override
    public OrderItemCustomer update(OrderItemCustomer orderItemCustomer) {
        return orderItemCustomerRepository.saveAndFlush(orderItemCustomer);
    }

}
