package com.javatechie.awselasticbeanstalkexample.repository;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.Like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LikeRepository extends JpaRepository<Like,Long>{
    @Query("SELECT l from like l WHERE  l.company= :company")
    Like findByCompany(Company company);

}
