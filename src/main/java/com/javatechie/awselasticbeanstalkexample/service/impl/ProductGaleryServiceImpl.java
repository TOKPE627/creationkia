package com.javatechie.awselasticbeanstalkexample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.ProductGalery;
import com.javatechie.awselasticbeanstalkexample.repository.ProductGaleryRepository;
import com.javatechie.awselasticbeanstalkexample.service.ProductGaleryService;

@Service
public class ProductGaleryServiceImpl implements ProductGaleryService {

	@Autowired
	private ProductGaleryRepository productGaleryRepository;
	
	@Override
	public ProductGalery findById(Long id) {
		return productGaleryRepository.getById(id);
	}

	@Override
	public ProductGalery update(ProductGalery productGalery) {
		return productGaleryRepository.saveAndFlush(productGalery);
	}

	@Override
	public ProductGalery save(ProductGalery productGalery) {
		return productGaleryRepository.save(productGalery);
	}





}
