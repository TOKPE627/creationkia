package com.javatechie.awselasticbeanstalkexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.CompanyGalery;
import com.javatechie.awselasticbeanstalkexample.domain.CompanyType;
import com.javatechie.awselasticbeanstalkexample.domain.User;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	Company findByUser(User user);
	  @Query("SELECT c from company c WHERE c.companyType  = :companyType")
	  List<Company> findAllByType(CompanyType companyType);
	  Company findByGalery(CompanyGalery companyGalery);
	  
}
