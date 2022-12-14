package com.javatechie.awselasticbeanstalkexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.repository.ProductRepository;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService{
   @Autowired
   private ProductRepository productRepository;

@Override
public Product save(Product product) {
    return productRepository.save(product);
}

@Override
public List<Product> findAll() {
    return productRepository.findAll();
}

@Override
public Product findById(Long id) {
    return productRepository.getById(id);
}

@Override
public Product update(Product product) {
    return productRepository.saveAndFlush(product);
}

@Override
public void remove(Long id) {
    productRepository.deleteById(id);
}

@Override
public Product findByName(String name) {
    return productRepository.findByName(name);
}

@Override
public List<Product> findByCategory(Category c) {
    return productRepository.findByCategory(c);
}

 

   
}
