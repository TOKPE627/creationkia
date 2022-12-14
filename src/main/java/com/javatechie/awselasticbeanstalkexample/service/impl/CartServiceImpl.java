package com.javatechie.awselasticbeanstalkexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.Cart;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.repository.CartRepository;
import com.javatechie.awselasticbeanstalkexample.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    
    @Override
    public Cart save(Cart cart) {
       return cartRepository.save(cart);
    }

    @Override
    public Cart findById(Long id){
       return cartRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        Cart cart = this.findById(id);
        if(cart !=null) {
            cartRepository.deleteById(id);
        }     
    }

    @Override
    public List<Cart> findByIpaddress(String ipaddress) {
       return cartRepository.findByIpaddress(ipaddress);
    }

    @Override
    public Cart update(Cart cart) {
        return cartRepository.saveAndFlush(cart);
    }

    @Override
    public Cart findByProductAndIpaddress(Product product, String ipaddress) {
       return cartRepository.findByProductAndIpaddress(product, ipaddress);
    }

}
