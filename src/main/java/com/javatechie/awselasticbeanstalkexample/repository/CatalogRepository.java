package com.javatechie.awselasticbeanstalkexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.awselasticbeanstalkexample.domain.Catalog;
import com.javatechie.awselasticbeanstalkexample.domain.User;

public interface CatalogRepository extends JpaRepository<Catalog, Long>{
	List<Catalog> findAll();
	List<Catalog> findByUser(User user);
}
