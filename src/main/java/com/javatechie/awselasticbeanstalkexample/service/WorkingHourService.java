package com.javatechie.awselasticbeanstalkexample.service;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.WorkingHour;

public interface WorkingHourService {
	WorkingHour findById(Long id);
	WorkingHour update(WorkingHour workingHour);
	WorkingHour save(WorkingHour workingHour);
	List<WorkingHour> findAll();
	List<WorkingHour> findByUser(User user);
	List<WorkingHour> findByDay(String day);
	void remove(Long id);
	List<WorkingHour> findByUserByDay(User user,String day);


}
