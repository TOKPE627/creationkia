package com.javatechie.awselasticbeanstalkexample.repository;

import org.springframework.data.repository.CrudRepository;

import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
	UserRole findByUser(User user);
}