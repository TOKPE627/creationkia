package com.javatechie.awselasticbeanstalkexample.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.security.Role;
import com.javatechie.awselasticbeanstalkexample.repository.RoleRepository;
import com.javatechie.awselasticbeanstalkexample.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role findByname(String name) {
        return roleRepository.findByname(name);
    }
    @Override
    public Role findOne(Long id) {
        return roleRepository.findByRoleId(id);
    }
    @Override
    public void update(Role role) {
        roleRepository.saveAndFlush(role);
    }
    



}
