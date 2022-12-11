package com.javatechie.awselasticbeanstalkexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.awselasticbeanstalkexample.domain.ProductGalery;

public interface ProductGaleryRepository extends JpaRepository<ProductGalery, Long>{
	
}
