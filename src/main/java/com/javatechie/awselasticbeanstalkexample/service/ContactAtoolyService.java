package com.javatechie.awselasticbeanstalkexample.service;

import com.javatechie.awselasticbeanstalkexample.domain.ContactAtooly;

public interface ContactAtoolyService {
	ContactAtooly findById(Long id);
    ContactAtooly update(ContactAtooly contactAtooly);
	ContactAtooly save(ContactAtooly contactAtooly);
    ContactAtooly findByName(String name);
}
