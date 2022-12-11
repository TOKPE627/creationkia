package com.javatechie.awselasticbeanstalkexample.service;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.Brand;

public interface BrandService {
    Brand add(Brand brand);

    Brand update(Brand brand);
   
    void delete(Long id);
    List<Brand> findAll();
    Brand findById(Long id);
    Brand findByName(String name);
}
