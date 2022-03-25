package com.javatechie.awselasticbeanstalkexample.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.WorkingHour;
import com.javatechie.awselasticbeanstalkexample.repository.WorkingHourRepository;
import com.javatechie.awselasticbeanstalkexample.service.WorkingHourService;

@Service
public class WorkingHourServiceImpl implements WorkingHourService{
  
	@Autowired
	private WorkingHourRepository workingHourRepository;

	@Override
	public WorkingHour findById(Long id) {
		return workingHourRepository.getById(id);
	}

	@Override
	public WorkingHour update(WorkingHour workingHour) {
		return workingHourRepository.saveAndFlush(workingHour);
	}

	@Override
	public WorkingHour save(WorkingHour workingHour) {
		return workingHourRepository.save(workingHour);
	}

	@Override
	public List<WorkingHour> findAll() {
		return workingHourRepository.findAll();
	}

	@Override
	public List<WorkingHour> findByUser(User user) {
		return workingHourRepository.findByUser(user);
	}

	@Override
	public List<WorkingHour> findByDay(String day) {
		return workingHourRepository.findByDay(day);
	}

	@Override
	public void remove(Long id) {
      workingHourRepository.deleteById(id);		
	}

	@Override
	public List<WorkingHour> findByUserByDay(User user, String day) {
		return workingHourRepository.findByUserByDay(user, day);
	}

	




	
	
}
