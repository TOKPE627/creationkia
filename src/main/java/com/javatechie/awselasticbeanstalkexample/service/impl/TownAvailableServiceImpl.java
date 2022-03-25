package com.javatechie.awselasticbeanstalkexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.TownAvailable;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.repository.TownAvailableRepository;
import com.javatechie.awselasticbeanstalkexample.service.TownAvailableService;

@Service
public class TownAvailableServiceImpl implements TownAvailableService{

	@Autowired
	private TownAvailableRepository townAvailableRepository;
	@Override
	public List<TownAvailable> findByUser(User user) {
		return townAvailableRepository.findByUser(user);
	}

	@Override
	public TownAvailable save(TownAvailable townAvailable) {
		return townAvailableRepository.save(townAvailable);
	}

	@Override
	public void remove(Long id) {
		townAvailableRepository.deleteById(id);	
	}
  
}
