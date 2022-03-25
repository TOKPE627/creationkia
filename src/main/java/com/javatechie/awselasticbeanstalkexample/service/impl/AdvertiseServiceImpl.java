package com.javatechie.awselasticbeanstalkexample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.Advertise;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.repository.AdvertiseRepository;
import com.javatechie.awselasticbeanstalkexample.service.AdvertiseService;

@Service
public class AdvertiseServiceImpl implements AdvertiseService{

	@Autowired
	private AdvertiseRepository advertiseRepository;
	
	@Override
	public Advertise findByUser(User user) {
		return advertiseRepository.findByUser(user);
	}

	@Override
	public Advertise save(Advertise advertise) {
		return advertiseRepository.save(advertise);
	}

	@Override
	public Advertise update(Advertise advertise) {
		return advertiseRepository.saveAndFlush(advertise);
	}

	@Override
	public Advertise findById(Long id) {
		return advertiseRepository.getById(id);
	}

	@Override
	public Advertise findByName(String name) {
		return advertiseRepository.findByName(name);
	}

}
