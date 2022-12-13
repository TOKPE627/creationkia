package com.javatechie.awselasticbeanstalkexample.service;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.Category;

public interface CategoryService {
    Category findById(Long id);
    Category update(Category c);
    Category save(Category c);
    List<Category> findAll();
    void remove(Long id);
    Category findByName(String name);

}
