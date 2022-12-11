package com.javatechie.awselasticbeanstalkexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.Style;
import com.javatechie.awselasticbeanstalkexample.repository.StyleRepository;
import com.javatechie.awselasticbeanstalkexample.service.StyleService;


@Service
public class StyleServiceImpl implements StyleService {

    @Autowired
    private StyleRepository styleRepository;
    
    @Override
    public Style add(Style style) {
       return styleRepository.save(style);
    }

    @Override
    public Style update(Style style) {
        return styleRepository.saveAndFlush(style);
    }

    @Override
    public void delete(Long id)  {
        Style s = this.findById(id);
        if(s !=null) {
            styleRepository.deleteById(id);
        }
    }
    @Override
    public List<Style> findAll() {
        return styleRepository.findAll();
    }

    @Override
    public Style findById(Long id)  {
        return styleRepository.getById(id);
    }

    @Override
    public Style findByName(String name) {
       return styleRepository.findByName(name);
    }

   

}
