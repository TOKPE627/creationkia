package com.javatechie.awselasticbeanstalkexample.service;

import com.javatechie.awselasticbeanstalkexample.domain.Customer;

public interface CustomerService {
	Customer add(Customer customer);

	Customer findById(Long id);
	
	Customer update(Customer customer);
}
