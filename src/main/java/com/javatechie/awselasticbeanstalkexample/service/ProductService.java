package com.javatechie.awselasticbeanstalkexample.service;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Product;

public interface ProductService {
    Product save(Product product);
	List<Product> findAll();
	Product findById(Long id);
	Product findByName(String name);
    Product update(Product product);
    void remove(Long id);
    List<Product> findByCategory(Category c);
   }
