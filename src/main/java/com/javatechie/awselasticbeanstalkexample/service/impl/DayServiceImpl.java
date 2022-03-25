package com.javatechie.awselasticbeanstalkexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.Day;
import com.javatechie.awselasticbeanstalkexample.repository.DayRepository;
import com.javatechie.awselasticbeanstalkexample.service.DayService;


@Service
public class DayServiceImpl implements DayService{

	@Autowired
	private DayRepository dayRepository;

	@Override
	public List<Day> findAll() {
		return dayRepository.findAll();
	}


}
