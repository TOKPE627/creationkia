package com.javatechie.awselasticbeanstalkexample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.repository.UserRoleRepository;
import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleRepository userRoleRepository;
	@Override
	public UserRole findByUser(User user) {
		return userRoleRepository.findByUser(user);
	}

}
