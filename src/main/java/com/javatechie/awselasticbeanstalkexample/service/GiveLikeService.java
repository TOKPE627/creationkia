package com.javatechie.awselasticbeanstalkexample.service;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.GiveLike;

public interface GiveLikeService {
    GiveLike save(GiveLike giveLike);
    GiveLike update(GiveLike giveLike);
    GiveLike findByCompany(Company company);
    GiveLike findByCompanyAndIpaddress(Company company, String ipaddress);
    List<GiveLike> findAllByCompany(Company company);
}
