package com.javatechie.awselasticbeanstalkexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.Univers;
import com.javatechie.awselasticbeanstalkexample.repository.UniversRepository;
import com.javatechie.awselasticbeanstalkexample.service.UniversService;


@Service
public class UniversServiceImpl implements UniversService {
   @Autowired
   private UniversRepository universRepository;
   
   @Override
   public Univers add(Univers univers) {
     return universRepository.save(univers);
   }

   @Override
   public Univers update(Univers univers) {
      return universRepository.saveAndFlush(univers);
   }

   @Override
   public void delete(Long id)  {
       Univers u = this.findById(id);
       if(u !=null) {
           universRepository.deleteById(id);
       }
   }
    @Override
    public List<Univers> findAll() {
        return universRepository.findAll();
    }

    @Override
    public Univers findById(Long id) {
        return universRepository.getById(id);
    }

    @Override
    public Univers findByName(String name) {
       return universRepository.findByName(name);
    }

   

}
