package com.javatechie.awselasticbeanstalkexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javatechie.awselasticbeanstalkexample.domain.Brand;


@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>{
    Brand findByName(String name);
}
