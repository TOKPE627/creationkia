package com.javatechie.awselasticbeanstalkexample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javatechie.awselasticbeanstalkexample.domain.OrderCustomer;
import com.javatechie.awselasticbeanstalkexample.repository.OrderCustomerRepository;
import com.javatechie.awselasticbeanstalkexample.service.OrderCustomerService;


@Service
public class OrderCustomerServiceImpl implements OrderCustomerService{

    @Autowired
    private OrderCustomerRepository orderCustomerRepository;

    @Override
    public OrderCustomer findByOrderTrackingNumber(String orderTrackingNumber) {
        return orderCustomerRepository.findByOrderTrackingNumber(orderTrackingNumber);
    }
    
    
}
