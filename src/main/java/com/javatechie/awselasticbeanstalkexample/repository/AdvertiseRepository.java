package com.javatechie.awselasticbeanstalkexample.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.awselasticbeanstalkexample.domain.Advertise;
import com.javatechie.awselasticbeanstalkexample.domain.User;


public interface AdvertiseRepository extends JpaRepository<Advertise, Long>{
	Advertise findByUser(User user);
	Advertise findByName(String name);
}
