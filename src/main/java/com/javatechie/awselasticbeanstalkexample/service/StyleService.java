package com.javatechie.awselasticbeanstalkexample.service;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.Style;


public interface StyleService {
    Style add(Style style);

    Style update(Style style);
   
    void delete(Long id);
    
    List<Style> findAll();
    Style findById(Long id);
    Style findByName(String name);
}
