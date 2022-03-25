package com.javatechie.awselasticbeanstalkexample.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.awselasticbeanstalkexample.domain.security.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByname(String name);
	Role findByRoleId(Long id);
}
