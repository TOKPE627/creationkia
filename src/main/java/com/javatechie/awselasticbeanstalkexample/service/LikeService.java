package com.javatechie.awselasticbeanstalkexample.service;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.Like;

public interface LikeService {
    Like findById(Long id);
    Like save(Like like);
    Like update(Like like);
    Like findByCompany(Company company);

}
