package com.javatechie.awselasticbeanstalkexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.awselasticbeanstalkexample.domain.Town;

public interface TownRepository extends JpaRepository<Town, Long>{
  List<Town> findAll();
}
