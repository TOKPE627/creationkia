package com.javatechie.awselasticbeanstalkexample.service.impl;

import com.javatechie.awselasticbeanstalkexample.domain.ContactAtooly;
import com.javatechie.awselasticbeanstalkexample.repository.ContactAtoolyRepository;
import com.javatechie.awselasticbeanstalkexample.service.ContactAtoolyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactAtoolyServiceImpl implements ContactAtoolyService{
    
    @Autowired
    private ContactAtoolyRepository contactAtoolyRepository;
    
    @Override
    public ContactAtooly findById(Long id) {
        return contactAtoolyRepository.getById(id);
    }

    @Override
    public ContactAtooly update(ContactAtooly contactAtooly) {
        return contactAtoolyRepository.saveAndFlush(contactAtooly);
    }

    @Override
    public ContactAtooly save(ContactAtooly contactAtooly) {
        return contactAtoolyRepository.save(contactAtooly);
    }

    @Override
    public ContactAtooly findByName(String name) {
        return contactAtoolyRepository.findByName(name);
    }
    
}
