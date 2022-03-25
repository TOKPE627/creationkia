package com.javatechie.awselasticbeanstalkexample.service;

import com.javatechie.awselasticbeanstalkexample.domain.Delivery;
import com.javatechie.awselasticbeanstalkexample.domain.User;

public interface DeliveryService {
  Delivery findById(Long id);
  Delivery save(Delivery delivery);
  Delivery update(Delivery delivery);
  Delivery findByCustomerAndPaymentStatus(User customer, User seller, String payment_status);
}
