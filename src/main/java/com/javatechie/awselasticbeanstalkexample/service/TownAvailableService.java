package com.javatechie.awselasticbeanstalkexample.service;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.TownAvailable;
import com.javatechie.awselasticbeanstalkexample.domain.User;

public interface TownAvailableService {
	  List<TownAvailable> findByUser(User user);
	  TownAvailable save(TownAvailable townAvailable);
		void remove(Long id);
}
