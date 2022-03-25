package com.javatechie.awselasticbeanstalkexample.service;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.Catalog;
import com.javatechie.awselasticbeanstalkexample.domain.User;

public interface CatalogService {
	Catalog findById(Long id);
    Catalog update(Catalog catalog);
	Catalog save(Catalog catalog);
	List<Catalog> findAll();
	List<Catalog> findByUser(User user);
	void remove(Long id);


}
