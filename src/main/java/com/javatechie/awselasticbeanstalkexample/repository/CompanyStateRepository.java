package com.javatechie.awselasticbeanstalkexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.awselasticbeanstalkexample.domain.CompanyState;
import com.javatechie.awselasticbeanstalkexample.domain.User;

public interface CompanyStateRepository extends JpaRepository<CompanyState, Long>{
	List<CompanyState> findAll();
	List<CompanyState> findByUser(User user);
}
