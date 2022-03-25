package com.javatechie.awselasticbeanstalkexample.service;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.User;

public interface CategoryService {
	Category findById(Long id);
	Category findById(int id);

    Category update(Category category);
	Category save(Category category);
	List<Category> findAll();
	List<Category> findByUser(User user);
	void remove(Long id);
	Category findByTitle(String title);
	List<Category> findAllBytTitle1Title2(String title1, String title2);
}
