package com.javatechie.awselasticbeanstalkexample.repository;
import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.GiveLike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GiveLikeRepository extends JpaRepository<GiveLike,Long>
  {
    GiveLike findByCompany(Company company);

    @Query("SELECT g from give_like g WHERE  g.company= :company AND  g.ipaddress = :ipaddress")
    GiveLike findByCompanyAndIpaddress(Company company, String ipaddress);
    
    @Query("SELECT g from give_like g WHERE  g.company= :company")
    List<GiveLike> findAllByCompany(Company company);
}
