package com.javatechie.awselasticbeanstalkexample.service.impl;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.Like;
import com.javatechie.awselasticbeanstalkexample.repository.LikeRepository;
import com.javatechie.awselasticbeanstalkexample.service.LikeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService{

    @Autowired
    private LikeRepository likeRepository;
    @Override
    public Like save(Like like) {
        return likeRepository.save(like);
    }

    @Override
    public Like findByCompany(Company company) {
        return likeRepository.findByCompany(company);
    }

    @Override
    public Like update(Like like) {
        return likeRepository.saveAndFlush(like);
    }

    @Override
    public Like findById(Long id) {
        return likeRepository.getById(id);
    }
    
}
