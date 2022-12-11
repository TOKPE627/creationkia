package com.javatechie.awselasticbeanstalkexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.Gender;
import com.javatechie.awselasticbeanstalkexample.repository.GenderRepository;
import com.javatechie.awselasticbeanstalkexample.service.GenderService;

@Service
public class GenderServiceImpl implements GenderService {
   
    @Autowired
    private GenderRepository genderRepository;
    
    @Override
    public List<Gender> findAll() {
        return genderRepository.findAll();
    }

    @Override
    public Gender findById(Long id){
        return genderRepository.getById(id);
    }

    @Override
    public Gender findByName(String name) {
       return genderRepository.findByName(name);
    }

    
}
