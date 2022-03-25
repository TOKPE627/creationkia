package com.javatechie.awselasticbeanstalkexample.service;

import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;

public interface UserRoleService {
	UserRole findByUser(User user);


}
