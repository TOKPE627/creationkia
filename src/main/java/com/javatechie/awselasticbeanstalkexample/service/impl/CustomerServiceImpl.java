package com.javatechie.awselasticbeanstalkexample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.Customer;
import com.javatechie.awselasticbeanstalkexample.repository.CustomerRepository;
import com.javatechie.awselasticbeanstalkexample.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public Customer add(Customer customer) {
       return customerRepository.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.getById(id);

    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.saveAndFlush(customer);

    }

}
