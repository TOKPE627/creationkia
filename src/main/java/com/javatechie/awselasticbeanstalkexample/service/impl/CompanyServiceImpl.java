package com.javatechie.awselasticbeanstalkexample.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.CompanyGalery;
import com.javatechie.awselasticbeanstalkexample.domain.CompanyType;
import com.javatechie.awselasticbeanstalkexample.domain.SubCategory;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.repository.CompanyRepository;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Company findById(Long id) {
		return companyRepository.getById(id);
	}

	@Override
	public Company update(Company company) {
		return companyRepository.save(company);
	}

	@Override
	public Company save(Company company) {
		return companyRepository.save(company);
	}


	@Override
	public Company findByUser(User user) {
		return companyRepository.findByUser(user);
	}

	@Override
	public List<Company> findAllByType(CompanyType companyType) {
		return companyRepository.findAllByType(companyType);
	}


    @Override
    public Company findByGalery(CompanyGalery companyGalery) {
    	return companyRepository.findByGalery(companyGalery);
    }

	@Override
	public List<Company> findByCompanyTypeBySubCategory(CompanyType companyType, SubCategory subCategory) {
		return companyRepository.findByCompanyTypeBySubCategory(companyType, subCategory);
	}


	@Override
	public List<Company> findByNameLike(String keyword) {
		return companyRepository.findByNameContainingIgnoreCase(keyword);
	}

	
}
