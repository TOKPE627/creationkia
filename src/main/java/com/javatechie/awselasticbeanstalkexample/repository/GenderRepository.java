package com.javatechie.awselasticbeanstalkexample.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javatechie.awselasticbeanstalkexample.domain.Gender;


@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {
    Gender findByName(String name);
}
