package com.javatechie.awselasticbeanstalkexample.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javatechie.awselasticbeanstalkexample.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	User findByEmail(String email);
	//User findById(User user);

	/*
	 * Optional<User> findByUsername(String username); Boolean
	 * existsByUsername(String username); Boolean existsByEmail(String email);
	 */
	   // Optional<User> findById(Long id);

}
