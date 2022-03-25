package com.javatechie.awselasticbeanstalkexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.awselasticbeanstalkexample.domain.Day;

public interface DayRepository extends JpaRepository<Day, Long>{
	List<Day> findAll();
}
