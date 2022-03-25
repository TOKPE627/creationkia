package com.javatechie.awselasticbeanstalkexample.service;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.Speciality;
import com.javatechie.awselasticbeanstalkexample.domain.User;

public interface SpecialityService {
	Speciality findById(Long id);
    Speciality update(Speciality specificity);
	Speciality save(Speciality specificity);
	List<Speciality> findAll();
	List<Speciality> findByUser(User user);
	void remove(Long id);
}

