package com.javatechie.awselasticbeanstalkexample.repository;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.GiveLike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GiveLikeRepository extends JpaRepository<GiveLike,Long>
  {
    //@Query("SELECT l from like l WHERE  l.company= :company")
    GiveLike findByCompany(Company company);

}
