package com.javatechie.awselasticbeanstalkexample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.Delivery;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.repository.DeliveryRepository;
import com.javatechie.awselasticbeanstalkexample.service.DeliveryService;

@Service
public class DeliveryServiceImpl implements DeliveryService{

	@Autowired
	private DeliveryRepository deliveryRepository;
	@Override
	public Delivery save(Delivery delivery) {
		return deliveryRepository.save(delivery);
	}
	@Override
	public Delivery update(Delivery delivery) {
		return deliveryRepository.saveAndFlush(delivery);
	}

	@Override
	public Delivery findById(Long id) {
		return deliveryRepository.getById(id);
	}

	@Override
	public Delivery findByCustomerAndPaymentStatus(User customer, User seller, String payment_status) {
		return deliveryRepository.findByCustomerAndPaymentStatus(customer, seller, payment_status);
	}
  
}
