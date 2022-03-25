package com.javatechie.awselasticbeanstalkexample.service;

import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.ProductGalery;

public interface ProductGaleryService {
	ProductGalery findById(Long id);
	ProductGalery update(ProductGalery productGalery);
	ProductGalery save(ProductGalery productGalery);
}
