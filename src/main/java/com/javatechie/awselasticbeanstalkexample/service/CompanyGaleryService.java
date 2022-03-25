package com.javatechie.awselasticbeanstalkexample.service;
import com.javatechie.awselasticbeanstalkexample.domain.CompanyGalery;

public interface CompanyGaleryService {
	CompanyGalery findById(Long id);
	CompanyGalery update(CompanyGalery companyGaley);
	CompanyGalery save(CompanyGalery company);

}
