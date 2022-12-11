package com.javatechie.awselasticbeanstalkexample.service;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.Gender;


public interface GenderService {
    List<Gender> findAll();
    Gender findById(Long id);
    Gender findByName(String name);
}
