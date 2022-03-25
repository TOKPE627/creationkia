package com.javatechie.awselasticbeanstalkexample.service;


import com.javatechie.awselasticbeanstalkexample.domain.security.Role;

public interface RoleService {
	Role findByname(String name);
	Role findOne(Long id);
    void update(Role role);
}
