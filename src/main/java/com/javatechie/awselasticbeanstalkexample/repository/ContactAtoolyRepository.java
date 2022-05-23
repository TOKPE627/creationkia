package com.javatechie.awselasticbeanstalkexample.repository;

import com.javatechie.awselasticbeanstalkexample.domain.ContactAtooly;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactAtoolyRepository extends JpaRepository<ContactAtooly, Long> {
    ContactAtooly findByName(String name);
}
