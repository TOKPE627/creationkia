package com.javatechie.awselasticbeanstalkexample.service;
import com.javatechie.awselasticbeanstalkexample.domain.OrderItemCustomer;

public interface OrderItemCustomerService {
    OrderItemCustomer add(OrderItemCustomer orderItemCustomer);
    OrderItemCustomer update(OrderItemCustomer orderItemCustomer);
}
