package com.javatechie.awselasticbeanstalkexample.service;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.PartnerAtooly;

public interface PartnerAtoolyService {
	PartnerAtooly findById(Long id);
    PartnerAtooly update(PartnerAtooly partner);
	PartnerAtooly save(PartnerAtooly partner);
    List<PartnerAtooly> findAllPartners();
    void remove(Long id);
}
