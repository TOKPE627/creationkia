package com.javatechie.awselasticbeanstalkexample.repository;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.PartnerAtooly;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerAtoolyRepository extends JpaRepository<PartnerAtooly, Long> {
  
}
