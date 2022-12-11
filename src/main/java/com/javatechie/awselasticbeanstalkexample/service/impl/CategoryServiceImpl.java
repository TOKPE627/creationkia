package com.javatechie.awselasticbeanstalkexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.repository.CategoryRepository;
import com.javatechie.awselasticbeanstalkexample.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    
    
    @Override
    public Category findById(Long id) {
        return categoryRepository.getById(id);
    }

    @Override
    public Category update(Category c) {
        return categoryRepository.saveAndFlush(c);
    }

    @Override
    public Category save(Category c) {
        return categoryRepository.save(c);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }


    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }


}
