package com.javatechie.awselasticbeanstalkexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.awselasticbeanstalkexample.domain.Speciality;
import com.javatechie.awselasticbeanstalkexample.domain.User;

public interface SpecialityRepository extends JpaRepository<Speciality, Long>{
	
	List<Speciality> findAll();
	
	List<Speciality>  findByUser(User user);
}
