package com.javatechie.awselasticbeanstalkexample.service;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.Univers;

public interface UniversService {

    Univers add(Univers univers);

    Univers update(Univers univers);
   
    void delete(Long id);
    
    List<Univers> findAll();
    Univers findById(Long id);
    Univers findByName(String name);
}
