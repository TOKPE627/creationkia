package com.javatechie.awselasticbeanstalkexample.service;
import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.CompanyGalery;
import com.javatechie.awselasticbeanstalkexample.domain.CompanyType;
import com.javatechie.awselasticbeanstalkexample.domain.SubCategory;
import com.javatechie.awselasticbeanstalkexample.domain.User;

public interface CompanyService {
	Company findById(Long id);
	Company update(Company company);
	Company save(Company company);
	Company findByUser(User user);
	List<Company> findAllByType(CompanyType companyType);
    Company findByGalery(CompanyGalery companyGalery);
	List<Company> findByCompanyTypeBySubCategory(CompanyType companyType, SubCategory subCategory);

}
