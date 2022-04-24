package com.javatechie.awselasticbeanstalkexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.Speciality;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.repository.SpecialityRepository;
import com.javatechie.awselasticbeanstalkexample.service.SpecialityService;

@Service
public class SpecialityServiceImpl implements SpecialityService{
	@Autowired
	private SpecialityRepository specificityRepository;

	@Override
	public Speciality findById(Long id) {
		return specificityRepository.getById(id);
	}

	@Override
	public Speciality update(Speciality specificity) {
		return specificityRepository.saveAndFlush(specificity);
	}

	@Override
	public Speciality save(Speciality specificity) {
		return specificityRepository.save(specificity);
	}

	@Override
	public List<Speciality> findAll() {
		return specificityRepository.findAll();
	}

	@Override
	public List<Speciality> findByUser(User user) {
		return specificityRepository.findByUser(user);
	}

	@Override
	public void remove(Long id) {
		specificityRepository.deleteById(id);
	}

	@Override
	public List<Speciality> findTop4ByUser(Long userId) {
		return specificityRepository.findTop4ByUser(userId);
	}

}
