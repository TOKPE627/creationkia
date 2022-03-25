package com.javatechie.awselasticbeanstalkexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.CompanyGalery;

public interface CompanyGaleryRepository extends JpaRepository<CompanyGalery, Long> {
}
