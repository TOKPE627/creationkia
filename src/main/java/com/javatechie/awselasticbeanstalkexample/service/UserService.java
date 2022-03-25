package com.javatechie.awselasticbeanstalkexample.service;

import java.util.Set;

import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.security.PasswordResetToken;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;



public interface UserService {
	PasswordResetToken getPasswordResetToken(final String token);

	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);
	
	User findByUsername(String username);
	
	/*
	 * User findOne(Long id);
	 */	
	void createPasswordResetTokenForUser(final User user, final String token);
		
	User findByEmail (String email);
	
	
	 User findById(Long id);
	
	//User findById(User id);
	

}
