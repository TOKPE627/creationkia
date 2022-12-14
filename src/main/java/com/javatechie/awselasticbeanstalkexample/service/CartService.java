package com.javatechie.awselasticbeanstalkexample.service;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.Cart;
import com.javatechie.awselasticbeanstalkexample.domain.Product;


public interface CartService {
    Cart save(Cart cart);
    Cart findById(Long id);
    void delete(Long id);
    Cart update(Cart cart);
        
    Cart  findByProductAndIpaddress(Product product, String ipaddress);
    
    List<Cart> findByIpaddress(String ipaddress);
}