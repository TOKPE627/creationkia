package com.javatechie.awselasticbeanstalkexample.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.Town;
import com.javatechie.awselasticbeanstalkexample.repository.TownRepository;
import com.javatechie.awselasticbeanstalkexample.service.TownService;

@Service
public class TownServiceImpl implements TownService{

	@Autowired
	private TownRepository townRepository;
	@Override
	public List<Town> findAll() {
		List<Town> townList = (List<Town>) townRepository.findAll();
		List<Town> activeTownList = new ArrayList<>();
		
		for(Town town: townList) {
			activeTownList.add(town);
		}
		
		return activeTownList;
	}

}
