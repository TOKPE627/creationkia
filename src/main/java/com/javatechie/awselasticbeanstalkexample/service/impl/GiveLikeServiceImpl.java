package com.javatechie.awselasticbeanstalkexample.service.impl;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.GiveLike;
import com.javatechie.awselasticbeanstalkexample.repository.GiveLikeRepository;
import com.javatechie.awselasticbeanstalkexample.service.GiveLikeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiveLikeServiceImpl implements GiveLikeService{
    @Autowired
    private GiveLikeRepository giveLikeRepository;

    @Override
    public GiveLike save(GiveLike giveLike) {
        return giveLikeRepository.save(giveLike);
    }

    @Override
    public GiveLike update(GiveLike giveLike) {
        return giveLikeRepository.saveAndFlush(giveLike);
    }

    @Override
    public GiveLike findByCompany(Company company) {
        return giveLikeRepository.findByCompany(company);
    }

    @Override
    public GiveLike findByCompanyAndIpaddress(Company company, String ipaddress) {
        return giveLikeRepository.findByCompanyAndIpaddress(company, ipaddress);
    }

    @Override
    public List<GiveLike> findAllByCompany(Company company) {
        return giveLikeRepository.findAllByCompany(company);
    }
    
    
}
