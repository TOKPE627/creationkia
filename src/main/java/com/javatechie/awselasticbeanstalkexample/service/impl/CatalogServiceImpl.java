package com.javatechie.awselasticbeanstalkexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.Catalog;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.repository.CatalogRepository;
import com.javatechie.awselasticbeanstalkexample.service.CatalogService;

@Service
public class CatalogServiceImpl implements CatalogService{
	
	@Autowired
	private CatalogRepository catalogRepository;

	@Override
	public Catalog findById(Long id) {
		return catalogRepository.getById(id);
	}

	@Override
	public Catalog update(Catalog catalog) {
		return catalogRepository.saveAndFlush(catalog);
	}

	@Override
	public Catalog save(Catalog catalog) {
		return catalogRepository.save(catalog);
	}

	@Override
	public List<Catalog> findAll() {
		return catalogRepository.findAll();
	}

	@Override
	public List<Catalog> findByUser(User user) {
		return catalogRepository.findByUser(user);
	}

	@Override
	public void remove(Long id) {
		catalogRepository.deleteById(id);
	}

}
