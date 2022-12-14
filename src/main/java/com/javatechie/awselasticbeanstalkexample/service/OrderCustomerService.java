package com.javatechie.awselasticbeanstalkexample.service;
import com.javatechie.awselasticbeanstalkexample.domain.OrderCustomer;

public interface OrderCustomerService {
    OrderCustomer add(OrderCustomer orderCustomer);
    OrderCustomer update(OrderCustomer orderCustomer);
    OrderCustomer findByOrderTrackingNumber(String orderTrackingNumber);

}