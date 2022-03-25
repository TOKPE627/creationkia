package com.javatechie.awselasticbeanstalkexample.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.CompanyGalery;
import com.javatechie.awselasticbeanstalkexample.repository.CompanyGaleryRepository;
import com.javatechie.awselasticbeanstalkexample.service.CompanyGaleryService;

@Service
public class CompanyGaleryServiceImpl implements CompanyGaleryService {

	@Autowired
	private CompanyGaleryRepository companyGaleryRepository;
	
	
	@Override
	public CompanyGalery findById(Long id) {
		return companyGaleryRepository.getById(id);
	}

	@Override
	public CompanyGalery update(CompanyGalery companyGalery) {
		return companyGaleryRepository.saveAndFlush(companyGalery);
	}

	@Override
	public CompanyGalery save(CompanyGalery companyGalery) {
		return companyGaleryRepository.save(companyGalery);
	}





}
