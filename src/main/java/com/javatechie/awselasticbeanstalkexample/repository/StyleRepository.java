package com.javatechie.awselasticbeanstalkexample.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javatechie.awselasticbeanstalkexample.domain.Style;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {
    Style findByName(String name);
}
