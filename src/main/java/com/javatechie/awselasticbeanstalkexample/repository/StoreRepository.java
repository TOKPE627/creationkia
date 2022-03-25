package com.javatechie.awselasticbeanstalkexample.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.awselasticbeanstalkexample.domain.Product;

public interface StoreRepository extends JpaRepository<Product, Long>{
 
}
