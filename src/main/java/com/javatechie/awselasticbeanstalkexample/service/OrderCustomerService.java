package com.javatechie.awselasticbeanstalkexample.service;

import com.javatechie.awselasticbeanstalkexample.domain.OrderCustomer;

public interface OrderCustomerService {
    OrderCustomer findByOrderTrackingNumber(String orderTrackingNumber);

}