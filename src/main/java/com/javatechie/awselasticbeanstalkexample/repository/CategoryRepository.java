package com.javatechie.awselasticbeanstalkexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javatechie.awselasticbeanstalkexample.domain.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    List<Category> findAll();
}

