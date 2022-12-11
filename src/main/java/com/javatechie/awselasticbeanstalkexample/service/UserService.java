package com.javatechie.awselasticbeanstalkexample.service;

import java.util.Set;

import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;



public interface UserService {
	
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);
	
	User findByUsername(String username);
			
	User findByEmail (String email);
	
	
	 User findById(Long id);
	
	

}
