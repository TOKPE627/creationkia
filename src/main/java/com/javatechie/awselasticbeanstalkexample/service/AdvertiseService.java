package com.javatechie.awselasticbeanstalkexample.service;

import com.javatechie.awselasticbeanstalkexample.domain.Advertise;
import com.javatechie.awselasticbeanstalkexample.domain.User;

public interface AdvertiseService {
	Advertise save(Advertise advertise);
	Advertise update(Advertise advertise);
	Advertise findByUser(User user);
	Advertise findById(Long id);
	Advertise findByName(String name);
}
