package com.javatechie.awselasticbeanstalkexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.awselasticbeanstalkexample.domain.TownAvailable;
import com.javatechie.awselasticbeanstalkexample.domain.User;

public interface TownAvailableRepository extends JpaRepository<TownAvailable, Long>{
  List<TownAvailable> findByUser(User user);
}
