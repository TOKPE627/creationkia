package com.javatechie.awselasticbeanstalkexample.service.impl;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.PartnerAtooly;
import com.javatechie.awselasticbeanstalkexample.repository.PartnerAtoolyRepository;
import com.javatechie.awselasticbeanstalkexample.service.PartnerAtoolyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerAtoolyServiceImpl implements PartnerAtoolyService {
    
    @Autowired
    private PartnerAtoolyRepository partnerAtoolyRepository;

    @Override
    public PartnerAtooly findById(Long id) {
        return partnerAtoolyRepository.getById(id);
    }

    @Override
    public PartnerAtooly update(PartnerAtooly partner) {
        return partnerAtoolyRepository.saveAndFlush(partner);
    }

    @Override
    public PartnerAtooly save(PartnerAtooly partner) {
        return partnerAtoolyRepository.save(partner);
    }

   

    @Override
    public void remove(Long id) {
     partnerAtoolyRepository.deleteById(id);   
    }

    @Override
    public List<PartnerAtooly> findAllPartners() {
        return partnerAtoolyRepository.findAll();
    }
    
}
